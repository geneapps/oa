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
import com.ydt.oa.dao.MaterialDao;
import com.ydt.oa.dao.SupplierDao;
import com.ydt.oa.entity.Material;
import com.ydt.oa.entity.Supplier;


/**
 * 材料表逻辑层
 * @author caochun
 *
 */
@Service
@Transactional(readOnly = false, noRollbackFor = Exception.class)
public class MaterialService {

	@Autowired
	private MaterialDao materialDao;
	@Autowired
	private SupplierDao supplierDao;


	public Pagination list(int currPage, int pageSize) {

		return materialDao.list(currPage, pageSize);
	}
	
	public Pagination lookupMaterial(Material material,int currPage,int pageSize) {

		return materialDao.lookupMaterial(material, currPage, pageSize);
	}
	public Pagination findMaterial(Material material,int currPage,int pageSize) {

		return materialDao.findMaterial(material, currPage, pageSize);
	}
   public void del(Material material) {
	   materialDao.delete(material);
   }
   public Material findMaterialById(Material material) {
	   return materialDao.findById(material.getId());
   }
   public void updateMaterial(Material material) {
	   materialDao.updateMaterial(material);
   }
	public List<Material> suggest(String suggestFields, String keywords) {
		
		return materialDao.suggest(suggestFields, keywords);

	}
	
	public Supplier fingSupplierByName(String name){
		
		return null;
		
	}
	
	public void importMaterial(File file) throws GiroException{
		
		List<Material> list = new ArrayList<Material>();
		
		// 创建对Excel工作簿文件的引用  
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new GiroException(-1,"文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
			throw new GiroException(-1,"文件不存在");
		}  
		// 创建对工作表的引用。  
		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"） 
		
		int sheetSize = workbook.getNumberOfSheets();
		
		HSSFSheet sheet = null;
		Material material = null;
		Supplier supplier = null;
		for(int i=0;i<sheetSize;i++){
			sheet = workbook.getSheetAt(i);
			
			if(sheet!=null){
				int rowSize = sheet.getLastRowNum();
				HSSFRow row = null;

				for(int j=1;j<rowSize;j++){
					row = sheet.getRow(j);
					// 材料名称	品牌  规格 单位  单价 供货商
					if(row!=null){
						material = new Material();
						material.setCategory(sheet.getSheetName());							
						material.setMaterialName(getCellValue(row.getCell(0)));
						material.setBrand(getCellValue(row.getCell(1)));
						material.setMaterialType(getCellValue(row.getCell(2)));
						material.setUnit(getCellValue(row.getCell(3)));
						material.setPrice(getCellDoubleValue(row.getCell(4)));
						material.setRemark(getCellValue(row.getCell(5)));
						String supplierName = getCellValue(row.getCell(6));
						
						if(supplierName.length()>0){
							supplier = supplierDao.findBySupplierName(supplierName);
							
							if(supplier!=null){
								material.setSupplier(supplier);
							}else{
								supplier = new Supplier();
								supplier.setSupplyName(supplierName);
								supplierDao.saveOrUpdate(supplier);
								material.setSupplier(supplier);
							}
							
						}						
//					
////						material.setUnit(row.getCell(3).getStringCellValue());
//						System.out.println(material.getBrand());
//						System.out.println(material.getCategory());
//						System.out.println(material.getId());
//						System.out.println(material.getMaterialName());
//						System.out.println(material.getMaterialType());
//						System.out.println(material.getPrice());
//						System.out.println(material.getUnit());
//						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
						list.add(material);
					}					
				}				
			}
		}		
		materialDao.saveOrUpdateAll(list);	

	}
	
	private String getCellDoubleValue(HSSFCell cell) {

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
				Double num = new Double(cell.getNumericCellValue());
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
	
	public static void main(String[] args){
		MaterialService s = new MaterialService();
		try {
			s.importMaterial(new File("d:/test.xls"));
		} catch (GiroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 


}
