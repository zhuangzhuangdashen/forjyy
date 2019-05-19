package com.example.admincf.lx.dao;

import com.example.admincf.lx.pojo.po.User;

import java.util.Set;

public interface UserDao {

	public User getByUserName(String username);
	
	public Set<String> getByUserNameRole(String username);
	
}
