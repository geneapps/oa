package com.ydt.oa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giro.common.dao.Pagination;
import com.giro.common.exception.GiroException;
import com.giro.common.util.AmountUtils;
import com.giro.common.util.DateUtils;
import com.giro.common.util.IntUtils;
import com.giro.common.util.StringUtils;
import com.ydt.oa.dao.MaterialDao;
import com.ydt.oa.dao.PurchaseApplyDao;
import com.ydt.oa.dao.PurchaseApplyDetailsDao;
import com.ydt.oa.dao.StoreRoomDao;
import com.ydt.oa.dao.StoreRoomLogDao;
import com.ydt.oa.dao.StoreRoomMaterialDao;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Project;
import com.ydt.oa.entity.PurchaseApply;
import com.ydt.oa.entity.PurchaseApplyDetails;
import com.ydt.oa.entity.StoreRoom;
import com.ydt.oa.entity.StoreRoomLog;
import com.ydt.oa.entity.StoreRoomMaterial;
import com.ydt.oa.entity.User;



/**
 * 材料表逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class StoreRoomService {

	@Autowired
	private StoreRoomDao storeRoomDao;
	@Autowired
	private StoreRoomMaterialDao storeRoomMaterialDao;
	@Autowired
	private MaterialDao materialDao;
	@Autowired
	private StoreRoomLogDao storeRoomLogDao;
	@Autowired
	private PurchaseApplyDao purchaseApplyDao;
	@Autowired
	private PurchaseApplyDetailsDao purchaseApplyDetailsDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private PurchaseApplyService purchaseApplyService;
   
	/**
	 * 根据库房查出对应的库房材料
	 * @param id
	 * @return
	 */
	public List<StoreRoomMaterial> list(String id){		
        return storeRoomMaterialDao.list(id) ;
	}
	/**
	 * 查出库房材料
	 * @return
	 */
	public Pagination listStoreRoomMaterial(int currPage,int pageSize){		
        return storeRoomMaterialDao.listStoreRoomMaterial(currPage, pageSize);
	}
	/**
	 * 查出入库库房材料
	 * @return
	 */
	public Pagination listStoreRoomLog(int currPage,int pageSize){		
        return storeRoomLogDao.listStoreRoomLog(currPage, pageSize);
	}
	/**
	 * 查出出库库房材料
	 * @return
	 */
	public Pagination listStoreRoomLogout(int currPage,int pageSize){		
        return storeRoomLogDao.listStoreRoomLogout(currPage, pageSize);
	}
	/**
	 * 根据领用人查询出库情况
	 * @return
	 */
	public Pagination listStoreRoomLogoutByUser(User user,int currPage,int pageSize){		
        return storeRoomLogDao.listStoreRoomLogoutByUser(user,currPage, pageSize);
	}
	/**
	 * 根据库房查出对应的材料
	 * @param id
	 * @return
	 *//*
	public List<Material> materiallist(String id) {
		List<StoreRoomMaterial> list=this.storeRoomMaterialDao.list(id);
		List<Material> materiallist=new ArrayList<Material>();
		for(int i=0;i<list.size();i++) {
			Material m = materialDao.findById(list.get(i).getMaterial().getId());
			materiallist.add(m);
		}
		return materiallist;
	}*/
	
	public Pagination list(int currPage, int pageSize) {

		return storeRoomDao.list(currPage, pageSize);
	}
     public List<StoreRoom> list(Project project){
    	
    	//System.out.println( user.getDepartment().getDepName() + " -----------");
        return storeRoomDao.list(project) ;
	}
    /**
     * 这里的库房更新待会要做一个判断
     * @param storeRoom
     */
     public void saveStoreRoom(StoreRoom storeRoom) {
    	 //System.out.println(storeRoom.getUser().getRealName());
    	 User user=userService.findByUserName(storeRoom.getUser().getRealName());
    	 storeRoom.setUser(user);
    	 Project project=projectService.findByName(storeRoom.getProject().getName());
    	 storeRoom.setProject(project);
    	 if(StringUtils.isNull(storeRoom.getId())){
    		 storeRoom.setCreateTime(DateUtils.getNowDateStr());
    		 //保存库房信息
    		 storeRoomDao.saveOrUpdate(storeRoom);
    	 } else {
    		 StoreRoom storeRoomold=storeRoomDao.findById(storeRoom.getId());
    		 storeRoomold.setName(storeRoom.getName());
    		 storeRoomold.setUser(storeRoom.getUser());
    		 storeRoomold.setProject(storeRoom.getProject());
    		 storeRoomold.setDescription(storeRoom.getDescription());
    		 storeRoomDao.saveOrUpdate(storeRoomold);
    		 //修改库房信息
    	 }
    	 
    	 
     }
     /**
      * 删除
      * @param storeRoom
      */
    public void deleteStoreRoom(StoreRoom storeRoom) {
    	storeRoom=storeRoomDao.findById(storeRoom.getId());
    	storeRoom.setStatus(StoreRoom.STOREROOM_DELETE);
    	 storeRoomDao.saveOrUpdate(storeRoom);
    }
    
    /**
     * 入库操作
     * @param purchase
     * @param purchaseApplyDetails
     * @throws GiroException 
     */
    public void purchaseIncoming(PurchaseApply purchase, List<PurchaseApplyDetails> purchaseApplyDetails) throws GiroException{
    	
    	// 更新订单状态是否可以报销
    	PurchaseApply oldPurchase = purchaseApplyService.findById(purchase.getId());
    	
    	oldPurchase.setStatus(purchase.getStatus());
    	
//    	purchaseApplyDao.saveOrUpdate(oldPurchase);
    	
    	// 更新订单明细
    	List<PurchaseApplyDetails> detailList = new ArrayList<PurchaseApplyDetails>();
    	
    	PurchaseApplyDetails oldDetail = null;
    	
    	for(PurchaseApplyDetails detail:purchaseApplyDetails){
    		oldDetail = purchaseApplyDetailsDao.findById(detail.getId());
    		oldDetail.setActualPrice(detail.getActualPrice());
    		long number = oldDetail.getHouseNumber()+detail.getHouseNumber();
    		if (number > oldDetail.getNumber()) {
				throw new GiroException(-1, "入库数量大于采购数量");
			}else if (number < oldDetail.getNumber()) {
				oldDetail.setHouseNumber(number);
	    		oldPurchase.setStatus(PurchaseApply.PURCHASE_STATUS_STARTBUY);
	    		detailList.add(oldDetail);  			
			}
    		else if(number == oldDetail.getNumber()){
    		oldDetail.setHouseNumber(number);
    		oldPurchase.setStatus(PurchaseApply.PURCHASE_STATUS_STARTEXPENSE);
    		
    		detailList.add(oldDetail);  
			}
    	}
    	
    	purchaseApplyDetailsDao.saveOrUpdateAll(detailList);
    	
    	
    	// 创建入库单、入库明细以及更新仓库材料数量
       StoreRoom  storeRoom=storeRoomDao.findByProject(oldPurchase.getProject());
       
       for(int i=0;i<purchaseApplyDetails.size();i++) {
    	   oldDetail= purchaseApplyDetailsDao.findById(purchaseApplyDetails.get(i).getId());
    	   Material material =oldDetail.getMaterial();
    	   //保存日志
    	   User user=userService.findByUserId(oldPurchase.getUser().getId());
    	   StoreRoomLog storeRoomLog=new StoreRoomLog();
    	   storeRoomLog.setUser(user);
    	   storeRoomLog.setTitle(oldPurchase.getTitle());
    	   storeRoomLog.setApplyNo(purchase.getId());
           storeRoomLog.setLogTime(DateUtils.getYYYYMMDD(DateUtils.getNowDateStr()));
           storeRoomLog.setMaterial(material);
           storeRoomLog.setType(StoreRoomLog.HOUSELOG_TYPE1);
         //  StoreRoomLog storeRoomLogold =storeRoomLogDao.findByPurchaseId(purchase.getId());
//    	   if(storeRoomLogold!=null&&storeRoomLogold.getId()!=null) {
//    		   System.out.println(storeRoomLogold.getTitle() + storeRoomLogold.getNumber() + "+++++++++storeroomold");
//    		   System.out.println(oldDetail.getHouseNumber() + "---------oldDetail");
//    		   storeRoomLog.setNumber(oldDetail.getHouseNumber()-storeRoomLogold.getNumber());
//    	   }else{
    		   System.out.println("*************" + purchaseApplyDetails.get(i).getHouseNumber());
    		   storeRoomLog.setNumber(purchaseApplyDetails.get(i).getHouseNumber());
//    	   }
               storeRoomLogDao.saveOrUpdate(storeRoomLog);
           
           StoreRoomMaterial storeRoomMaterial=new StoreRoomMaterial();
           StoreRoomMaterial oldstoreRoomMaterial=  storeRoomMaterialDao.findByRoomAndMaterial(storeRoom.getId(), material.getId());
          
           if(oldstoreRoomMaterial!=null&&oldstoreRoomMaterial.getId()!=null) {
        	   //更新记录
        	   StoreRoomMaterial  oldstoreRoomMaterial1 =storeRoomMaterialDao.findById(oldstoreRoomMaterial.getId());
        	   oldstoreRoomMaterial1.setNumber(oldDetail.getHouseNumber());
        	   storeRoomMaterialDao.saveOrUpdate(oldstoreRoomMaterial1);
           }else {
        	   //增加一条记录
        	   storeRoomMaterial.setMaterial(material);
        	   storeRoomMaterial.setStoreRoom(storeRoom);
        	   storeRoomMaterial.setNumber(oldDetail.getHouseNumber());
        	   storeRoomMaterialDao.saveOrUpdate(storeRoomMaterial);
           }
        
           }
           
       }
       
    /**
     * 出库操作
     * @param purchase
     * @param storeRoomMaterialList
     */
    public void materialouting(StoreRoomLog storeRoomLog,List<StoreRoomMaterial> storeRoomMaterialList,List<Long> outNumbers) throws GiroException{
    	
    	
    	
    	// 创建出库单、出库明细以及更新仓库材料数量
       for(int i=0;i<storeRoomMaterialList.size();i++) {
    
    	   //保存日志
    	   long number = outNumbers.get(i).longValue();
    	   System.out.println("OutNumbers:"+number);
    	   StoreRoomMaterial storeRoomMaterial=storeRoomMaterialDao.findById(storeRoomMaterialList.get(i).getId());
    	   
    	   if(storeRoomMaterial==null){
    		  throw new GiroException(-1, "库房没有该材料");
    	   }
    	   
    	   if(number>storeRoomMaterial.getNumber()){
    		   throw new GiroException(-2, "库房材料不足");
    	   }
    	   
    	   //StoreRoomLog storeRoomLog=new StoreRoomLog();
    	   User user=userService.findByUserName(storeRoomLog.getUser().getRealName());
    	   storeRoomLog.setUser(user);
           storeRoomLog.setLogTime(DateUtils.getNowDateStr());
           System.out.println(storeRoomMaterialList.get(i).getId());
           //StoreRoomMaterial storeRoomMaterial2=  storeRoomMaterialDao.findById(storeRoomMaterialList.get(i).getId());
           storeRoomLog.setMaterial(storeRoomMaterial.getMaterial());
           storeRoomLog.setType(StoreRoomLog.HOUSELOG_TYPE2);
    	   storeRoomLog.setNumber(number);
           storeRoomLogDao.saveOrUpdate(storeRoomLog);
           
         
        	   //更新记录
           storeRoomMaterial.setNumber(storeRoomMaterial.getNumber()-number);
           storeRoomMaterialDao.saveOrUpdate(storeRoomMaterial);
           
           
           ////////
          // 更新purchaseApply里面的houseNumber值
//           //首先根据project查询出purchaseApply的值
//          List<PurchaseApply> purchaseApplyList= purchaseApplyDao.findByProject(storeRoomMaterial.getStoreRoom().getProject().getId());
//           //根据purchaseApply的id值和material的id值来查询houseNumber
//           for(int j=0;j<purchaseApplyList.size();j++) {
//        	   PurchaseApplyDetails purchaseApplyDetails =purchaseApplyDetailsDao.
//        	   findByPurchaseApplyDetails(purchaseApplyList.get(j).getId(),storeRoomMaterial.getMaterial().getId());
//        	   
//        	   purchaseApplyDetails.setHouseNumber(storeRoomMaterial.getNumber());
//        	   purchaseApplyDetailsDao.saveOrUpdate(purchaseApplyDetails);
//           }
       }
           
           }
           
       
       /**
        * 转库出库操作
        * @param purchase
        * @param storeRoomMaterialList
        */
       public void turningstoreroom(StoreRoom storeRoom,String name,List<StoreRoomMaterial> storeRoomMaterialList,List<Long> outNumbers){
       	
		storeRoom = storeRoomDao.findByName(name);
		// 创建出库单、出库明细以及更新仓库材料数量
		for (int i = 0; i < storeRoomMaterialList.size(); i++) {
			long number = outNumbers.get(i).longValue();
			// 保存日志
			StoreRoomMaterial storeRoomMaterial = storeRoomMaterialDao.findById(storeRoomMaterialList.get(i).getId());
			StoreRoomMaterial oldstoreRoomMaterial = storeRoomMaterialDao.findByRoomAndMaterial(storeRoom.getId(),
					storeRoomMaterial.getMaterial().getId());
			// 更新转入方
			if (oldstoreRoomMaterial != null && oldstoreRoomMaterial.getId() != null) {
				// 更新记录
				oldstoreRoomMaterial.setNumber(number + oldstoreRoomMaterial.getNumber());
				storeRoomMaterialDao.saveOrUpdate(oldstoreRoomMaterial);
			} else {
				// 增加一条记录
				StoreRoomMaterial storeRoomMaterial2 = new StoreRoomMaterial();
				storeRoomMaterial2.setMaterial(storeRoomMaterial.getMaterial());
				storeRoomMaterial2.setStoreRoom(storeRoom);
				storeRoomMaterial2.setNumber(number);
				storeRoomMaterialDao.saveOrUpdate(storeRoomMaterial2);
			}
			StoreRoomLog storeRoomLog = new StoreRoomLog();
			storeRoomLog.setLogTime(DateUtils.getNowDateStr());
			System.out.println(storeRoomMaterialList.get(i).getId());
			// StoreRoomMaterial storeRoomMaterial2=
			// storeRoomMaterialDao.findById(storeRoomMaterialList.get(i).getId());
			storeRoomLog.setMaterial(storeRoomMaterial.getMaterial());
			storeRoomLog.setType(StoreRoomLog.HOUSELOG_TYPE2);
		
			storeRoomLog.setNumber(number);
			storeRoomLogDao.saveOrUpdate(storeRoomLog);
			// 更新记录
			storeRoomMaterial.setNumber(storeRoomMaterial.getNumber() - number);
			storeRoomMaterialDao.saveOrUpdate(storeRoomMaterial);
		}
       }

       public List<StoreRoomLog> findStoreRoomByAppId(String id){
			return storeRoomLogDao.findStoreRoomByAppId(id);
		}
       
       public StoreRoom findStoreRoomByProject(Project project){
			return storeRoomDao.findByProject(project);
		}
       
       public List<StoreRoomLog> findStoreRoomLogByUser(String id){
			return storeRoomLogDao.findStoreRoomLogByUser(id);
		}
       
       public List<StoreRoomMaterial> findStoreRoomMaterialById(String id){
			return storeRoomMaterialDao.findStoreRoomMaterialById(id);
		}
		public List<StoreRoom> listAllStoreroom() {
	
			return storeRoomDao.listAllStoreroom();
		}
		public Pagination listStoreRoomMaterialByProject(StoreRoom storeRoom, int pageNum, int numPerPage) {
			
			return storeRoomDao.listStoreRoomMaterialByProject(storeRoom,pageNum,numPerPage);
		}
		public Pagination listStoreRoomLogoutbyTime(String logTime, int pageNum, int numPerPage) {

			return storeRoomLogDao.findStoreRoomByTime(logTime,pageNum,numPerPage);
		}
    }





