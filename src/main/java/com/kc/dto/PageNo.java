package com.kc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageNo {

	@JsonProperty(value = "pageNo")
	private int pageNo;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
