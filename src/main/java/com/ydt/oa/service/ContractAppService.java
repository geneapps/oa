package com.ydt.oa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.ContractDao;
import com.ydt.oa.dao.ContractDetailsDao;
import com.ydt.oa.dao.FileLogDao;
import com.ydt.oa.dao.ManContractDetailsDao;
import com.ydt.oa.entity.Application;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.ContractDetails;
import com.ydt.oa.entity.FileLog;
import com.ydt.oa.entity.ManContractDetails;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.User;
import com.ydt.oa.interfaces.ContractAppInterface;


/**
 * 系统设置业务逻辑层
 * @author caochun
 *
 */

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ContractAppService implements ContractAppInterface{
	

	@Autowired
	private ApproveService approveService;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private FileLogDao fileLogDao;
	@Autowired
	private ContractDetailsDao contractDetailsDao;
	@Autowired
	private ManContractDetailsDao manContractDetailsDao;	
	@Autowired
	private ContractService contractService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	private Contract contract;
	
  
	
	/**
	 * 更新合同申请单
	 * @param application
	 * @param purchaseApply
	 * @throws GiroException 
	 */
	@Override
	public void updateContractApp( String id ,Application application, Contract contract, List<FileLog> fileLogList, User user) throws GiroException {
		
		user = userService.findByUserId(user.getId());
		application.setUser(user);
		application.setDepartment(user.getDepartment());
		
		if(contract.getContractType()==Contract.CONTRACT_TYPE_MAN){
			// 人工合同
			application.setApplyType(Application.APP_TYPE_MANCONTRACT);
		}else{
			application.setApplyType(Application.APP_TYPE_MATERIALCONTRACT);
		}
		
//		application.setApplyType(Application.APP_TYPE_CONTRACT);
		Project project = projectService.findById(id);
		contract.setProject(project);
		
		contract.setTitle(application.getTitle());
		if(StringUtils.isNull(application.getId())){
			
			
			// 保存合同申请信息
			contract.setStatus(Contract.CONTRACT_WAITINGAPPROVE);
			contract.setUpdateTime(DateUtils.getNowDateStr());
			contractDao.saveOrUpdate(contract);
			
			List<ContractDetails> detailsList = contract.getContractDetails();
			
			if(detailsList!=null){			
				for(ContractDetails contractDetails:detailsList){
					//System.out.println(contractDetails.getId());
					//System.out.println(contractDetails.getNumber());
					contractDetails.setContract(contract);
				}
				contractDetailsDao.saveOrUpdateAll(detailsList);
			}
			
			List<ManContractDetails> manDetailsList = contract.getManContractDetails();
			
			if(manDetailsList!=null){			
				for(ManContractDetails detail:manDetailsList){
					//System.out.println(contractDetails.getId());
					//System.out.println(contractDetails.getNumber());
					detail.setContract(contract);
				}
				manContractDetailsDao.saveOrUpdateAll(manDetailsList);
			}

			if(fileLogList!=null){
				
				String[] ids = new String[fileLogList.size()];
				for(int i=0;i< fileLogList.size();i++){
					ids[i]= fileLogList.get(i).getId();
				}
				
				Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
				
				for(FileLog fileLog:fileLogs){
					fileLog.setBusinessType(Contract.FILE_TYPE);
					fileLog.setBusinessNo(contract.getId());
				}
				
				fileLogDao.saveOrUpdateAll(fileLogs);
			}
			
			
			
			// 将合同id保存到Application中
			application.setApplyNo(contract.getId());
			
			
			// 添加新的申请
			approveService.createApp(application,user);
			
		}else{
			
			// 修改申请
			approveService.updateApp(application,user);
			
			Contract oldContract = contractDao.findById(contract.getId());
				// 删除合同明细
				contractDetailsDao.deleteAll(oldContract.getContractDetails());
				manContractDetailsDao.deleteAll(oldContract.getManContractDetails());
				
				// 清楚文件绑定
				
				Collection<FileLog> oldFileList = fileLogDao.findByBusinessNo(Contract.FILE_TYPE, oldContract.getId());
				
				for(FileLog file:oldFileList){
					file.setBusinessNo(null);
				}
				fileLogDao.saveOrUpdateAll(oldFileList);
				
				// 保存采购申请信息
				oldContract.setTitle(contract.getTitle());
				oldContract.setProject(contract.getProject());
				oldContract.setCompanyA(contract.getCompanyA());
				oldContract.setCompanyB(contract.getCompanyB());
				oldContract.setMeterialContractType(contract.getMeterialContractType());
				oldContract.setB_contact(contract.getB_contact());
				oldContract.setB_contact_phone(contract.getB_contact_phone());
				oldContract.setContractReason(contract.getContractReason());
				oldContract.setStatus(Contract.CONTRACT_WAITINGAPPROVE);
				oldContract.setUpdateTime(DateUtils.getNowDateStr());
				contractDao.saveOrUpdate(oldContract);
				
				// 重新保存明细
				List<ContractDetails> detailsList = contract.getContractDetails();
				
				if(detailsList!=null){			
					for(ContractDetails contractDetails:detailsList){
//						System.out.println(contractDetails.getId());
//						System.out.println(contractDetails.getNumber());
						contractDetails.setContract(oldContract);
					}
					contractDetailsDao.saveOrUpdateAll(detailsList);
				}
				
				List<ManContractDetails> manDetailsList = contract.getManContractDetails();
				
				if(manDetailsList!=null){			
					for(ManContractDetails detail:manDetailsList){
						//System.out.println(contractDetails.getId());
						//System.out.println(contractDetails.getNumber());
						detail.setContract(contract);
					}
					manContractDetailsDao.saveOrUpdateAll(manDetailsList);
				}

				if(fileLogList!=null){
					
					String[] ids = new String[fileLogList.size()];
					for(int i=0;i< fileLogList.size();i++){
						ids[i]= fileLogList.get(i).getId();
					}
					
					Collection<FileLog> fileLogs = fileLogDao.findByIdList(ids);
					
					for(FileLog fileLog:fileLogs){
						fileLog.setBusinessType(Contract.FILE_TYPE);
						fileLog.setBusinessNo(oldContract.getId());
					}
					
					fileLogDao.saveOrUpdateAll(fileLogs);
				}
				
				

			
			
			
			
			
				
			
		}		
	}
	

	@Override
	public void deleteApp(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_MATERIALCONTRACT) || application.getApplyType().equals(Application.APP_TYPE_MANCONTRACT)){
			contract = contractService.findById(application.getApplyNo());
			contract.setStatus(Contract.CONTRACT_DELETE);
			contractDao.saveOrUpdate(contract);
		}
		
		
	}


	@Override
	public void approve(Application application) {

		// TODO Auto-generated method stub
		application = approveService.findById(application.getId());
		
		if(application.getApplyType().equals(Application.APP_TYPE_MATERIALCONTRACT) || application.getApplyType().equals(Application.APP_TYPE_MANCONTRACT)){
			contract = contractService.findById(application.getApplyNo());
			
			if(application.getStatus()==Application.APP_STATUS_APPROVED){
				contract.setStatus(Contract.CONTRACT_VALID);
			}else{
				contract.setStatus(Contract.CONTRACT_INVALID);
			}
			contractDao.saveOrUpdate(contract);
		}
		
		
	}
	

	
}
