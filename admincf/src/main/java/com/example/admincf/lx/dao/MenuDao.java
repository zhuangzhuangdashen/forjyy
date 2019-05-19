package com.example.admincf.lx.dao;

import com.example.admincf.lx.pojo.po.Menu;
import com.example.admincf.lx.pojo.vo.MenuVo;

import java.util.List;

/**
 * 菜单数据访问类
 * @author Administrator
 * @version 1.0
 * @date 2019-04-24 16:50
 */
public interface MenuDao {

	/**
	 * 获取所有的菜单集合
	 * @return
	 */
	public List<Menu> selectMenuListAll();
	
	/**
	 * 根据父级菜单ID获取菜单信息
	 * @param id
	 * @return
	 */
	public List<Menu> selectMenuById(int id);
	
	/**
	 * 获取菜单
	 * @return
	 */
	public List<Menu> getlistAll(MenuVo menuVo);
	/**
	 * 获取菜单总数
	 * @param menuVo
	 * @return
	 */
	public Integer getlistcount(MenuVo menuVo);
	
}
