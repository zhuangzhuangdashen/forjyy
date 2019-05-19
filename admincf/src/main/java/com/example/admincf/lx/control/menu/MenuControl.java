package com.example.admincf.lx.control.menu;

import com.example.admincf.lx.pojo.vo.MenuVo;
import com.example.admincf.lx.service.MenuService;
import com.example.admincf.lx.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 菜单控制器类
 * @author Administrator
 * @version 1.0
 * @date 2019 16:50
 */
@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuControl {
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("getMenuList")
	@ResponseBody
	public Result getMenuList(){
		try {
			Map<String,Object> map=this.menuService.getMenuListAll();
			if(map==null||map.size()==0){
				return new Result(true,"查询成功","001",null);
			}
			return new Result(true,"查询成功","001",map.get("data"));
		} catch (Exception ex) {
			log.error("查询失败，系统异常", ex);
			return new Result(true,"查询失败，系统异常","002",null);
		}
	}
	
	@RequestMapping("/mlistview")
	public ModelAndView mlistview(){
		ModelAndView mod=new ModelAndView();
		mod.setViewName("/menu/menu-list");
		return mod;
	}
	
	@RequestMapping("getlist")
	@ResponseBody
	public Result getlist(MenuVo menuVo){
		try {
			return new Result(true,"查询成功","0",this.menuService.getListAll(menuVo));
		} catch (Exception ex) {
			log.error("查询菜单数据失败，系统异常", ex);
			return new Result(true,"查询失败，系统异常","002",null);
		}		
	}
}
