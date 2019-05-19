package com.example.admincf.lx.dao;

import com.example.admincf.lx.pojo.po.ActivtiyCf;
import com.example.admincf.lx.pojo.vo.ActivtiyCfVo;

import java.util.List;

/**
 * 
* @Title: ActivtiyCfDao.java  
* @Package com.lx.dao  
* @Description: CF活动数据访问类
* @author lbk  
* @date 2019年4月28日 下午7:06:46  
* @version V1.0
 */
public interface ActivtiyCfDao {

	/**
	 * 获取act数据top 15集合数据
	 * @param act
	 * @return
	 */
	public List<ActivtiyCf> getActivListAll(ActivtiyCfVo act);

	/**
	 * 获取act数据总数
	 * @param act
	 * @return
	 */
	public Integer getActivCountAll(ActivtiyCfVo act);
	
}
