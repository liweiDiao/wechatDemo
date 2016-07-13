package com.dlw.weChat.model;

/**
 * 微信通用接口凭证
 * @author diaoliwei
 * @date 2016-2-25
 *
 */
public class AccessToken {

	//获取到的凭证
	private String token;
	
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
