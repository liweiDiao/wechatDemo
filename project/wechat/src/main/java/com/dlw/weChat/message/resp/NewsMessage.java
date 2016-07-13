package com.dlw.weChat.message.resp;

import java.util.List;

/**
 * 图文消息
 * @author diaoliwei
 * @date 2016-2-24
 *
 */
public class NewsMessage extends BaseMessage{
	
	private int ArticleCount;
	
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
