package com.dlw.weChat.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.dlw.weChat.model.WeathResultPair;
import com.dlw.weChat.model.WeatherInfo;
import com.google.gson.Gson;
import com.dlw.common.utils.GetProperties;
import com.dlw.weChat.message.resp.Article;
import com.dlw.weChat.message.resp.Music;
import com.dlw.weChat.message.resp.MusicMessage;
import com.dlw.weChat.message.resp.NewsMessage;
import com.dlw.weChat.message.resp.TextMessage;

import com.dlw.weChat.utils.MessageUtil;
import com.dlw.weChat.utils.StringUtil;
import com.dlw.weChat.service.CoreService;

@Service("coreService")
public class CoreServiceImpl implements CoreService{
	
	//域名
	private static String url = GetProperties.getConstValueByKey("url");

	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容 完全发布要删除
			String respContent = "请求！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(toUserName);
			textMessage.setToUserName(fromUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
				respMessage = this.getTextMessage(requestMap);
				return respMessage;
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
				String picUrl = requestMap.get("PicUrl");
//				textMessage.setContent("您发送的是图片信息！地址是：" + picUrl);
				String content = FaceServiceImpl.detect(picUrl);
				textMessage.setContent(content);
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {// 地理位置消息
				String resp = this.getLocationMessage(requestMap);
		        return resp;
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
				textMessage.setContent("您发送的是链接消息！");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 音频消息
				respMessage = this.getMusicMessage(requestMap);
				return respMessage;
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 事件推送
				// 事件类型 
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！我是大帅哥～～～刁立伟~哈哈，回复“?”显示帮助菜单";
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消订阅
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					System.out.println(eventKey);
					if (eventKey.equals("11")) {
						respContent = "--菜单项被点击！";
					} else if (eventKey.equals("12")) {
						respContent = "-----菜单项被点击！";
					}
				}
				textMessage.setContent(respContent);
			}
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(respMessage);
		return respMessage;
	}
	
	/**
	 * 文本消息回复
	 * @author diaoliwei
	 * @date 2016-2-24
	 * @param requestMap
	 * @return
	 */
	private String getTextMessage(Map<String, String> requestMap){
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		String respMessage = null;
		
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		
//		String createTime = StringUtil.formatTime(requestMap.get("CreateTime"));
		//内容
		String content = requestMap.get("Content");
		
			
		if(StringUtil.isQqFace(content)){
			textMessage.setContent(content);
		}else if("?".equals(content) || "？".equals(content)){
			StringBuilder sb = new StringBuilder();
			sb.append("您好，我是自动服务，请回复数字选择服务：").append("\n\n");
			sb.append("1    天气查询").append("\n");
			sb.append("2   人脸检测").append("\n");
//			sb.append("5    快递查询").append("\n");
			sb.append("回复“?”显示此帮助菜单");
			textMessage.setContent(sb.toString());
		}else if ("1".equals(content)){
			String msg = "发送您当前的位置，可查看天气状况：";
			textMessage.setContent(msg);
		} else if("2".equals(content)){
			StringBuffer buffer = new StringBuffer();
	        buffer.append("人脸检测使用指南").append("\n\n");
	        buffer.append("发送一张清晰的照片，就能帮你分析出种族、年龄、性别等信息").append("\n");
	        buffer.append("快来试试你是不是长得太着急");
			textMessage.setContent(buffer.toString());
		} else {
			textMessage.setContent("回复“?”显示帮助菜单");
		}
		respMessage = MessageUtil.textMessageToXml(textMessage);
		return respMessage;
	}
	
	/**
	 * 单图文消息
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param requestMap
	 * @return
	 */
	private String getNewsMessage(Map<String, String> requestMap){
		String respMessage = null;
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);
		
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("进度查询");
		article.setDescription("信息共享，公开查询！");
//		article.setPicUrl("/imgs/logo.png"); //TODO image图片路径
		article.setUrl("");//TODO 请求地址
		articleList.add(article);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		respMessage = MessageUtil.newsMessageToXml(newsMessage);
		return respMessage;
	}
	
	/**
	 * 音乐消息回复
	 * @author diaoliwei
	 * @date 2016-2-25
	 * @param requestMap
	 * @return
	 */
	private String getMusicMessage(Map<String, String> requestMap){
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		String respMessage = null;
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
		musicMessage.setFuncFlag(0);
		
		String content = requestMap.get("Recognition");
		Music music = new Music();
		music.setDescription("音乐信息");
		music.setTitle("音乐信息～～～～");
		music.setMusicUrl(content);
		music.setHQMusicUrl(content);
		musicMessage.setMusic(music);
		
		respMessage = MessageUtil.musicMessageToXml(musicMessage);
		return respMessage;
	}
	
	/**
	 * 发送地理位置，获取天气信息
	 * @param requestMap
	 * @author diaoliwei
	 * @date 2016-3-11 23:32
	 * @return
	 */
	private String getLocationMessage(Map<String, String> requestMap){
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		String label = requestMap.get("Label");
		label = label.substring(0, 3);
		int hour = new Date().getHours();
		WeatherInfo weatherinfo = new Gson().fromJson(BaiduWeatherService.getWeather(label),WeatherInfo.class);
		List<WeathResultPair> wRpair = new ArrayList<WeathResultPair>();
		wRpair = weatherinfo.getResults().get(0).getWeather_data();
		List<Article> articlelist = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle(label+"天气情况");
		article.setDescription("");
		article.setPicUrl("");
		article.setUrl("");
		articlelist.add(article);
		for(int i=0;i<wRpair.size();i++){
            Article article1=new Article();
            article1.setTitle(wRpair.get(i).getDate() + wRpair.get(i).getWeather() + wRpair.get(i).getWind() + wRpair.get(i).getTemperature());
            article1.setDescription("");
            if(hour >= 6 && hour <= 18){
                article1.setPicUrl(wRpair.get(i).getDayPictureUrl());
            }else {
                article1.setPicUrl(wRpair.get(i).getNightPictureUrl());
            }
            article1.setUrl(" ");
            articlelist.add(article1);  
        }
		NewsMessage newsMessage = new NewsMessage();
        newsMessage.setArticleCount(articlelist.size());
        newsMessage.setArticles(articlelist);
        newsMessage.setFuncFlag(0);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        String resp = MessageUtil.newsMessageToXml(newsMessage);
        return resp;
	}
}
