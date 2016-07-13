package com.dlw.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @Description:读取properties配置文件
 * @author diaoliwei
 * @date 2015-6-12 11:54:18
 */
public class GetProperties {

	private static Logger logger = Logger.getLogger(GetProperties.class);

	public static String CONFIG_PATH = "/config.properties";// 配置文件名称

	public GetProperties() {

	}

	/**
	 * 读取配置文件中的配置
	 * 
	 * @param key 配置名称
	 * @author diaoliwei
	 * @return
	 */
	public static String getConstValueByKey(String key) {
		String value = "";
		try {
			if(getPropertiesFromResorceStream().containsKey(key)){
				value = getPropertiesFromResorceStream().getProperty(key);
			}
		} catch (Exception e) {
			logger.info("读取文件失败!", e);
		}
		return value;
	}

	/**
	 * 加载Properties文件
	 * @作者 diaoliwei
	 * @创建时间 2015-7-15 21:55:34
	 * @return
	 */
	private static Properties getPropertiesFromResorceStream() {
		Properties prop = new Properties();
		try {
			// TODO 需修改为只加载一次就可，不需要每次获取属性都加载文件
			prop.load(getInputStream());
		} catch (IOException e) {
			logger.info("读取文件失败!", e);
		}
		return prop;
	}

	/**
	 * InputStream读取文件
	 * @作者 diaoliwei
	 * @创建时间 2015-7-15 21:53:51
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static InputStreamReader getInputStream() throws UnsupportedEncodingException {
		 return new InputStreamReader(GetProperties.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8");   
		//return GetProperties.class.getResourceAsStream(CONFIG_PATH);
	}
	
}
