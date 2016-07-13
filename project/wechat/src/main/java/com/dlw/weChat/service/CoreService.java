package com.dlw.weChat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 核心服务
 * @author diaoliwei
 * @date 2016-2-23
 * @Copyright dlw
 */
public interface CoreService {
	
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @author diaoliwei
	 * @return
	 */
	String processRequest(HttpServletRequest request);
}
