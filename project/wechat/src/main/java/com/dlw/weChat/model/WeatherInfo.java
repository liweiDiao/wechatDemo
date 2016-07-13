package com.dlw.weChat.model;

import java.util.List;

/**
 * 天气信息
 * @author diaoliwei
 * @date 2016-3-11 22:49
 *
 */
public class WeatherInfo {
	
	private String error;
	
	private String status;
	
	private String date;
	
	private List<WeatherResult> results;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<WeatherResult> getResults() {
		return results;
	}

	public void setResults(List<WeatherResult> results) {
		this.results = results;
	}
	
}
