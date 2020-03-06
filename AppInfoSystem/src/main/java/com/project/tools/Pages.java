package com.project.tools;

/**
 * 分页工具类
 * @author HP
 *
 */
public class Pages {
	private Integer totalCount;   //总记录数
	private Integer totalPageCount;  //总页数
	private Integer currentPageNo=1;     //当前页
	private Integer pageSize=5;   //每页记录数
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPageCount() {
		if(totalCount%pageSize==0){
			return totalCount/pageSize;
		}
		return totalCount/pageSize+1;
	}

	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得显示页第一条的记录位置
	 * @return
	 */
	public Integer getFirstRow(){
		return (currentPageNo-1)*pageSize;
	}
	
}
