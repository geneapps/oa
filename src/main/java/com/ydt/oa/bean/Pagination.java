package com.ydt.oa.bean;

/**
 * 分页对象
 * @author caochun
 *
 */
public class Pagination {
	public static int NUM_PER_PAGE = 20;
	private int numPerPage;
	private int totalCount;
	private int currentPage;
	private int totalPage;
	// 开始的索引值
	private int startindex;
	// 结束的索引值
	private int endindex;

	// 构造器
	public Pagination() {

	}
	public Pagination(int totalCount, int currentPage) {
		this.setTotalCount(totalCount);
		this.setCurrentPage(currentPage);
		this.setNumPerPage(NUM_PER_PAGE);
//		calculate();
	}

	public Pagination(int totalCount, int numPerPage, int currentPage) {

		this.setTotalCount(totalCount);
		this.setCurrentPage(currentPage);
		this.setNumPerPage(numPerPage);
//		calculate();
	}

	private void calculate() {
		
		if(numPerPage==0) numPerPage = NUM_PER_PAGE;

		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		startindex = (currentPage - 1) * numPerPage;
		if (startindex < 1)
			startindex = 1;
		// 计算总页数
		endindex = (currentPage + 1) * numPerPage;
		if (endindex >= totalCount)
			endindex = totalCount;
	}

	public int getNumPerPage() {

		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {

		if(numPerPage<0){
			this.numPerPage = NUM_PER_PAGE;
		}else{
			this.numPerPage = numPerPage;
		}
		calculate();
	}

	public int getTotalCount() {

		return totalCount;
	}

	public void setTotalCount(int totalCount) {

		if(totalCount<0){
			this.totalCount = 0;
		}else{
			this.totalCount = totalCount;
		}
		calculate();
	}

	public int getCurrentPage() {

		return currentPage;
	}

	public void setCurrentPage(int currentPage) {

		if(currentPage<1){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
		calculate();
	}

	public int getTotalPage() {

		return totalPage;
	}

	public int getStartindex() {

		return startindex;
	}

	public int getEndindex() {

		return endindex;
	}
}
