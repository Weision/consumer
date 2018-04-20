package com.wxx.entity;

import java.io.Serializable;

public class Body implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 722874611910668311L;
	private String method;
	private String header;
	private String body;
	private String url;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
