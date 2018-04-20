package com.wxx.entity;

public class Article {
	private String id;
	private Weixx weixx;
	private String title;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Weixx getWeixx() {
		return weixx;
	}

	public void setWeixx(Weixx weixx) {
		this.weixx = weixx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
