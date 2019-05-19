package com.example.admincf.lx.service;

import com.example.admincf.lx.pojo.vo.MenuVo;

import java.util.Map;

/**
 * 菜单业务接口类
 * @author Administrator
 * @version 1.0
 * @date 2019-04-24 17:17
 */
public interface MenuService {

	/**
	 * 获取菜单数据，并且转化菜单结构
	 * @return
	 */
	public Map<String,Object> getMenuListAll();
	
	

	/**
	 * 获取菜单数据集合
	 * @return
	 */
	public Map<String,Object> getListAll(MenuVo menuVo);
	
}
