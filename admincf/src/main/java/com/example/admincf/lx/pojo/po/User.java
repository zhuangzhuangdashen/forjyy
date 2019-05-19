package com.example.admincf.lx.pojo.po;

import java.io.Serializable;

public class User implements Serializable{

	private int id;
	
	private String username;
	
	private String pwd;
	
	private String state;
	
	private String pwdinfo;
	
	public String getPwdinfo() {
		return pwdinfo;
	}

	public void setPwdinfo(String pwdinfo) {
		this.pwdinfo = pwdinfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
