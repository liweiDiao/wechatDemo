package com.dlw.weChat.model;

import java.util.List;

public class ExpressSingleResult {
	
	//快递公司
	private String company;
	//快递公司简称
	private String com;
	//快件号
	private String no;
	//快件当前的情况
	private List<ExpressSingleList> list;
	//0或1。0表示此单号信息还有更新的可能；1表示此单号信息不会再更新（签收、退回等最终状态）。
	private String status;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public List<ExpressSingleList> getList() {
		return list;
	}

	public void setList(List<ExpressSingleList> list) {
		this.list = list;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
