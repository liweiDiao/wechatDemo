package com.dlw.weChat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dlw.weChat.service.QueryService;

@Service("queryService")
public class QueryServiceImpl implements QueryService {

//	@Autowired
//	private CommonDao baseCommonDao;
	
	@Override
	public List<?> getSchedule(String file_number) {
		if(StringUtils.isEmpty(file_number)){
			return null;
		}
		List<?> list = null;
		//TODO something
		return list;
	}

}
