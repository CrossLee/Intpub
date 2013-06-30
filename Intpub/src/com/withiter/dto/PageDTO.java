package com.withiter.dto;

import java.util.List;


public class PageDTO {

	private int totalRecords=0;
	@SuppressWarnings("unchecked")
	private List list;
	private int pageNo;
	private int pageSize;
	@SuppressWarnings("unused")
	private int getTotalPages = 1;
   
	
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo == 0){
			this.pageNo = 1;
		}else {
			this.pageNo = pageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}

	public int getTopPageNo() {
		return 1;
	}

	public int getPreviousPageNo() {
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo - 1;
	}

	public int getNextPageNo() {
		if (pageNo >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		}
		return pageNo + 1;
	}

	public int getBottomPageNo() {
		return getTotalPages() == 0 ? 1 : getTotalPages();
	}
}
