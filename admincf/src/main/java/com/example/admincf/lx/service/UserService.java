package com.example.admincf.lx.service;

import com.example.admincf.lx.pojo.po.User;

import java.util.Set;

public interface UserService {

	public User getByUserName(String username);
	
	public Set<String> getByUserNameRole(String username);
}
