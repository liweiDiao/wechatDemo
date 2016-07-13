package com.dlw.weChat.model;

/**
 * 快递当前情况
 * @author diaoliwei
 * @date 2016-3-12 1:07
 *
 */
public class ExpressSingleList {
	
	//时间
	private String datetime;
	
	//描述
	private String remark;
	
	//区域，视快递公司情况，不保证一定有信息
	private String zone;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
}
