package com.example.admincf.lx.pojo.po;

import java.io.Serializable;

public class Role implements Serializable{

	private int id;
	
	private String rolename;
	
	private String bz;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
