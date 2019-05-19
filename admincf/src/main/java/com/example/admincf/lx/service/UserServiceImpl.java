package com.example.admincf.lx.service;

import com.example.admincf.lx.dao.UserDao;
import com.example.admincf.lx.pojo.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Resource
	private UserDao userDao;
	
	@Override
	public User getByUserName(String username) {
		User user=this.userDao.getByUserName(username);
		return user;
	}

	@Override
	public Set<String> getByUserNameRole(String username) {
		return userDao.getByUserNameRole(username);
	}

}
