package com.example.admincf.lx.service;

import com.example.admincf.lx.dao.MenuDao;
import com.example.admincf.lx.pojo.po.Menu;
import com.example.admincf.lx.pojo.vo.MenuVo;
import com.example.admincf.lx.util.LxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * 菜单业务实现类
 * @author Administrator
 * @version 1.0
 * @date 2019-04-24 17:17
 */
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menuDao;

	@Override
	public Map<String, Object> getMenuListAll() {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		List<Menu> menu_list=this.menuDao.selectMenuListAll();
		if(menu_list==null||menu_list.size()==0){
			return map;
		}
		for (Menu menu : menu_list) {
			if(!LxUtil.isEmpty(menu.getParent_id())){//父亲带单
				List<Map<String, Object>> maplist1=new ArrayList<Map<String,Object>>();
				Map<String, Object> mcd=new LinkedHashMap<String, Object>();
				mcd.put("text", menu.getMenu_name());
				mcd.put("icon", menu.getMenu_icon());
				mcd.put("href",menu.getMenu_href());
				List<Menu> mlist=this.menuDao.selectMenuById(menu.getId());
				for (Menu menu2 : mlist) {
					if(!LxUtil.isEmpty(menu2.getMenu_href())){//父亲带单
						List<Map<String, Object>> maplist2=new ArrayList<Map<String,Object>>();
						Map<String, Object> mcd3=new LinkedHashMap<String, Object>();
						mcd3.put("text", menu2.getMenu_name());
						mcd3.put("icon", menu2.getMenu_icon());
						mcd3.put("href",menu2.getMenu_href());
						maplist1.add(mcd3);
						List<Menu> mlist3=this.menuDao.selectMenuById(menu2.getId());
						for (Menu menu3 : mlist3) {
							Map<String, Object> mcdparen3=new LinkedHashMap<String, Object>();
							mcdparen3.put("text", menu3.getMenu_name());
							mcdparen3.put("icon", menu3.getMenu_icon());
							mcdparen3.put("href",menu3.getMenu_href());
							maplist2.add(mcdparen3);
						}
						mcd3.put("subset3",maplist2);
					}else{
						Map<String, Object> mcdparent=new LinkedHashMap<String, Object>();
						mcdparent.put("text", menu2.getMenu_name());
						mcdparent.put("icon", menu2.getMenu_icon());
						mcdparent.put("href",menu2.getMenu_href());
						maplist1.add(mcdparent);
					}
				}
				mcd.put("subset",maplist1);
				maplist.add(mcd);
			}
		}
		map.put("data", maplist);
		return map;
	}

	@Override
	public Map<String, Object> getListAll(MenuVo menuVo) {
		Map<String,Object> map=new HashMap<String, Object>();
		Integer count=this.menuDao.getlistcount(menuVo);
		if(count==null||count==0){
			map.put("count", 0);
			map.put("list", null);
			return map;
		}
		List<Menu> list=this.menuDao.getlistAll(menuVo);
		map.put("count", count);
		map.put("list", list);
		return map;
	}

}
