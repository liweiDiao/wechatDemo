package com.dlw.weChat.message.req;

/**
 * 图片消息
 * @author diaoliwei
 * @date 2016-2-23
 *
 */
public class ImageMessage extends BaseMessage{

	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
}
