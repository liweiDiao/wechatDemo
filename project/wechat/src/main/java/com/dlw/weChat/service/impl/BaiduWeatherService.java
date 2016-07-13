package com.dlw.weChat.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dlw.weChat.utils.HttpRequestUtil;

/**
 * 调用百度天气查询天气状况
 * @author diaoliwei
 * @date 2016-2-26
 *
 */
public class BaiduWeatherService {

	public static String getWeather(String source) {
		String json = new String();
		// 组装查询地址
		String requestUrl = "http://api.map.baidu.com/telematics/v3/weather?location={keyWord}&output=json&ak=DGKyPBnLbxQWD276dGe97Fte";//ak=百度AK
		// 对参数q的值进行urlEncode utf-8编码
		requestUrl = requestUrl.replace("{keyWord}", HttpRequestUtil.urlEncodeUTF8(source));

		// 查询并解析结果
		try {
			// 查询并获取返回结果
			json = HttpRequestUtil.httpRequest(requestUrl);
			// 利用正则表达式去除json数据中形如转义字符
			/*
			 * Pattern p = Pattern.compile("\\\\/"); Matcher m =
			 * p.matcher(json); StringBuffer buf = new StringBuffer();
			 * while(m.find()){ m.appendReplacement(buf, "\\/"); }
			 * m.appendTail(buf); json=buf.toString();
			 */
			// json=unicodeToUtf8(json);
			// json=replaceBlank(json);
			// 通过Gson工具将json转换成TranslateResult对象
//			System.out.println(json);
			// 取出translateResult中的译文
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/** 
	 * 将json数据中的unicode编码转换为UTF-8编码格式，以便正常显示中文
	 * @param theString
	 * @return
	 */
	private static String unicodeToUtf8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't'){
						aChar = '\t';
					} else if (aChar == 'r'){
						aChar = '\r';
					} else if (aChar == 'n'){
						aChar = '\n';
					} else if (aChar == 'f'){
						aChar = '\f';
					}	
					outBuffer.append(aChar);
				}
			} else{
				outBuffer.append(aChar);
			}	
		}
		return outBuffer.toString();
	}

	/** 
	 * 去除json数据中的空格，换行等符号；
	 * @param str
	 * @return
	 */
	private static String replaceBlank(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}

}
