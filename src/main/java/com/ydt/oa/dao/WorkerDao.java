package com.ydt.oa.dao;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.giro.common.dao.BaseDaoHibernate;
import com.giro.common.dao.Pagination;
import com.ydt.oa.entity.Contract;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Supplier;
import com.ydt.oa.entity.Worker;
import com.ydt.oa.service.MaterialService;




/**
 *工人数据库层
 * @author caochun
 *
 */
@Repository
public class WorkerDao extends BaseDaoHibernate<Worker>{
	@Autowired
	private ContractorDao contractorDao;
	@SuppressWarnings("unchecked")
	
/*	public List<Material> suggest(String suggestFields, String keywords) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from Material");        
  
        	buf.append(" where "+suggestFields+" like ?" );
        	params.add('%'+keywords+'%');
        	
        	return (List<Material>) this.queryList(buf.toString(), params.toArray());
	}
	*//**
	 * 更新材料信息
	 * @param material
	 *//*
	public void updateMaterial(Material material) {
		
		if(StringUtils.isEmpty(material.getId())) {
			 this.saveOrUpdate(material);
		}else {
			//Supplier supplier=supplierDao.findBySupplierName(material.getSupplier().getSupplyName());
		
			Material materialold=this.findById(material.getId());
			materialold.setMaterialName(material.getMaterialName());
			materialold.setMaterialType(material.getMaterialType());
			materialold.setPrice(material.getPrice());
			//materialold.setSupplier(supplier);
			materialold.setBrand(material.getBrand());
			materialold.setUnit(material.getUnit());
			materialold.setRemark(material.getRemark());
			this.saveOrUpdate(materialold);
		}
	}
	public Pagination lookupMaterial(Material material,int currPage,int pageSize) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from Material where category like ? and materialName like ? and supplier.supplyName like ? and materialType like ?");
          		//"and  materialType like ? and brand like ?");        
          params.add('%'+material.getCategory()+'%');
          params.add('%'+material.getMaterialName()+'%');
          params.add('%'+material.getSupplier().getSupplyName()+'%');
          params.add('%'+material.getMaterialType()+'%');
      	 // params.add('%'+material.getBrand()+'%');
        	return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
    public Pagination findMaterial(Material material,int currPage,int pageSize) {
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
          buf.append("from Material where materialName like ?");
          params.add('%'+material.getMaterialName()+'%');
        return pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
	}
	public Pagination list(int currPage,int pageSize){		
		
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Material order by id DESC");
        return  pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
	*/
	//通过分包商id查找对应的工人
	public Pagination findWorkerByContractor(String id,int currPage,int pageSize){		
		
		System.out.println("-------------------------");
        StringBuilder buf=new StringBuilder();
        List<Object> params=new ArrayList<Object>();
        buf.append("from Worker w where w.contractor.id = ? ");
        params.add(id);
        return  pageQuery(buf.toString(), params.toArray(),currPage, pageSize);
		
	}
}
