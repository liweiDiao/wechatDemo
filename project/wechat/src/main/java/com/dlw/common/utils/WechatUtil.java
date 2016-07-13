package com.dlw.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.dlw.weChat.model.AccessToken;
import com.dlw.weChat.model.Menu;
import com.dlw.weChat.utils.CustomX509TrustManager;

/**
 * 公众平台通用接口工具类
 * 
 * @author diaoliwei
 * @date 2016-2-25
 *
 */
public class WechatUtil {

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 * @param appsecret
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				System.out.println("获取token失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	/**
	 * 创建菜单
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param menu 
	 * @param accessToken 有效的access_token
	 * @return
	 */
	public static int createMenu(Menu menu, String accessToken){
		int result = 0;
		//拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				System.out.println("创建菜单失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
			}
		}
		return result;
	}

	public static JSONObject httpRequest(String requestUrl,	String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuilder sb = new StringBuilder();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new CustomX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));// 防止乱码
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(sb.toString());
		} catch (ConnectException e) {
			System.out.println("微信服务连接超时");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO流异常");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("https请求异常");
			e.printStackTrace();
		}
		return jsonObject;
	}

}
