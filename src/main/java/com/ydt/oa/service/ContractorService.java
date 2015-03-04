package com.ydt.oa.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.DateUtils;
import com.ydt.oa.dao.ContractorDao;
import com.ydt.oa.dao.ProjectDao;
import com.ydt.oa.dao.WorkerDao;
import com.ydt.oa.entity.Contractor;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.Supplier;
import com.ydt.oa.entity.Worker;

@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class ContractorService {

	@Autowired
	private ContractorDao contractorDao;
	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectDao projectDao;

	// 分页
	public Pagination list(int currPage, int pageSize) {

		return contractorDao.list(currPage, pageSize);
	}

	// 通过供货商查找产品
	public Pagination findWorkerByContractor(String id, int currPage, int pageSize) {

		return workerDao.findWorkerByContractor(id, currPage, pageSize);
	}

	//
	public Contractor findByContractorId(String id) {

		if (id == null || "".equals(id)) {
			return null;
		} else {
			return contractorDao.findById(id);
		}
	}

	public void updateContractor(Contractor contractor, String id) {

		Project project = projectService.findById(id);
		contractor.setProject(project);
		contractorDao.saveOrUpdate(contractor);
	}

	public void importWorker(File file) throws GiroException {

		List<Worker> list = new ArrayList<Worker>();
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new GiroException(-1, "文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
			throw new GiroException(-1, "文件不存在");
		}
		// 创建对工作表的引用。
		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		int sheetSize = workbook.getNumberOfSheets();
		HSSFSheet sheet = null;
		Worker worker = null;
		Contractor contractor = null;
		for (int i = 0; i < sheetSize; i++) {
			sheet = workbook.getSheetAt(i);
			if (sheet != null) {
				int rowSize = sheet.getLastRowNum();
				HSSFRow row = null;
				for (int j = 1; j < rowSize; j++) {
					row = sheet.getRow(j);
					if (row != null) {
						worker = new Worker();
						// 工人的信息
						String workType = getCellValue(row.getCell(2));
						String workerName = getCellValue(row.getCell(1));
						String telephone = getCellValue(row.getCell(4));
						// 分包商的相关信息
						String contractorName = getCellValue(row.getCell(0));
						String credit = getCellValue(row.getCell(5));
						String projectName = getCellValue(row.getCell(3));
						Project project = projectService.findByName(projectName);

						if (contractorName.length()>0 &&"包工头".equals(workType)) {
							contractor = new Contractor();
							contractor.setContractorName(contractorName);
							contractor.setContact(workerName);
							contractor.setCredit(credit);
							contractor.setPhone(telephone);
							contractor.setProject(project);
							contractorDao.saveOrUpdate(contractor);
							System.out.println(contractor.getContractorName()+"+++++++++++++++++++++");
							}
							
						if(!"包工头".equals(workType) && workerName.length() > 0 && contractorName.equals(contractor.getContractorName())){
							worker.setContractor(contractor);
							worker.setWorkerName(workerName);
							worker.setWorkerType(workType);
							worker.setTelephone(telephone);	
							workerDao.saveOrUpdate(worker);
						}
							
										
						
						}
					}
				}
			}
		}



	private String getCellValue(HSSFCell cell) {

		if (cell == null) {
			return "";
		}
		String value = null;
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			value = cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				value = DateUtils.getDateStr(cell.getDateCellValue());
			} else if (DateUtil.isCellInternalDateFormatted(cell)) {
				value = DateUtils.getDateStr(cell.getDateCellValue());
			} else {
				Long num = new Long((long) cell.getNumericCellValue());
				value = String.valueOf(num);
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			value = cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			value = cell.toString();
		} else {
			value = cell.toString();
		}
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}
	
	public Pagination lookupContractor(Contractor contractor,int currPage,int pageSize) {

		return contractorDao.lookupContractor(contractor, currPage, pageSize);
	}
	
	public List<Contractor> suggest(String suggestFields, String keywords) {
		
		return contractorDao.suggest(suggestFields, keywords);

	}
}
