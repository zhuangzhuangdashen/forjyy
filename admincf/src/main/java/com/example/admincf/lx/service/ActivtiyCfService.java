package com.example.admincf.lx.service;

import com.example.admincf.lx.pojo.vo.ActivtiyCfVo;

import java.util.Map;

/**
 * 
* @Title: ActivtiyCfService.java  
* @Package com.lx.service  
* @Description: cf活动业务层  
* @author lbk  
* @date 2019年4月28日 下午7:13:06  
* @version V1.0
 */
public interface ActivtiyCfService {

	public Map<String,Object> getlistAll(ActivtiyCfVo vo);
	
}
