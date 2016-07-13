package com.dlw.weChat.model;

import java.util.List;

/**
 * 天气结果
 * @author diaoliwei
 * @date 2016-3-11 22:52
 *
 */
public class WeatherResult {
	
	public String currentCity;
	
	public List<WeathResultPair> weather_data;

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public List<WeathResultPair> getWeather_data() {
		return weather_data;
	}

	public void setWeather_data(List<WeathResultPair> weather_data) {
		this.weather_data = weather_data;
	}

}
