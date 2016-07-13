package com.dlw.weChat.message.req;

/**
 * 文本消息
 * @author diaoliwei
 * @date 2016-2-23
 *
 */
public class TextMessage extends BaseMessage{

	//消息内容
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
