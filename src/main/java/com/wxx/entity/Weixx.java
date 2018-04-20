package com.wxx.entity;

import java.io.Serializable;

public class Weixx implements Serializable {

	private static final long serialVersionUID = 4049329557651556222L;
	private String id;
	private String userid;
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
