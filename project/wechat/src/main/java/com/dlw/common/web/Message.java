package com.dlw.common.web;

import java.util.List;

/**
 * 
 * @Description:异步返回工具类
 * @author diaoliwei
 * @date 2015年10月10日 上午11:08:52
 */
public class Message {

	private long total;

	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
