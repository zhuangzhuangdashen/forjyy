package com.example.admincf.lx.dao;

import com.example.admincf.lx.pojo.po.ZuHaoWan;
import com.example.admincf.lx.pojo.vo.ZuHaoWanVo;

import java.util.List;

/**
 * 
* @Title: ZuHaoWanDao.java  
* @Package com.lx.dao  
* @Description: TODO租号玩数据访问层 
* @author lbk  
* @date 2019年4月25日 上午1:14:05  
* @version V1.0
 */
public interface ZuHaoWanDao {

	public void saveZuHaoWan(ZuHaoWan zuHaoWan);
	
	public List<ZuHaoWan> getZhuHaoWanlistAll(ZuHaoWanVo zuHaoWan);
	
	public Integer getZhuHaoWanlistCount(ZuHaoWanVo zuHaoWan);
}
