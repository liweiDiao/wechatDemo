package com.dlw.weChat.model;

import java.util.List;

/**
 * 快递信息
 * @author diaoliwei
 *
 */
public class ExpressSingleRes {
	
	//返回标识码
	private String resultcode;
	
	private String reason;
	
	private List<ExpressSingleResult> result;
	
	private String error_cdoe;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<ExpressSingleResult> getResult() {
		return result;
	}

	public void setResult(List<ExpressSingleResult> result) {
		this.result = result;
	}

	public String getError_cdoe() {
		return error_cdoe;
	}

	public void setError_cdoe(String error_cdoe) {
		this.error_cdoe = error_cdoe;
	}
	
}
