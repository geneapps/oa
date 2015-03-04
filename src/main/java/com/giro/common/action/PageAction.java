package com.giro.common.action;


public class PageAction extends BaseAction {
	private int pageNum;
	private int numPerPage;
	
	public int getPageNum() {
		
		if(pageNum<1) return 1;
		else return pageNum;
	}
	
	public void setPageNum(int pageNum) {
	
		this.pageNum = pageNum;
	}
	
	public int getNumPerPage() {
		if(numPerPage<1) return 20;
		return numPerPage;
	}
	
	public void setNumPerPage(int numPerPage) {
	
		this.numPerPage = numPerPage;
	}

	
}
