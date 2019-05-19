package com.example.admincf.lx.service;

import com.example.admincf.lx.dao.ActivtiyCfDao;
import com.example.admincf.lx.pojo.vo.ActivtiyCfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActivtiyCfServiceImpl implements ActivtiyCfService {
	
	@Autowired
	private ActivtiyCfDao activtiyCfDao;

	@Override
	public Map<String, Object> getlistAll(ActivtiyCfVo vo) {
		Integer count=this.activtiyCfDao.getActivCountAll(vo);
		if(count==null||count==0){
			return null;
		}
		Map<String, Object> map=new HashMap<>();
		map.put("count", count);
		map.put("list", this.activtiyCfDao.getActivListAll(vo));
		return map;
	}

}
