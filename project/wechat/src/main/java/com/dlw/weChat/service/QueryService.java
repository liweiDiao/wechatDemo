package com.dlw.weChat.service;

import java.util.List;

public interface QueryService {
	
	/**
	 * 进度查询
	 * @date 2016-3-2
	 * @author diaoliwei
	 * @param file_number
	 * @return
	 */
	List<?> getSchedule(String file_number);
	
}
