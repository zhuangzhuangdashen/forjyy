package com.example.admincf.lx.control.cf;

import com.example.admincf.lx.pojo.vo.ActivtiyCfVo;
import com.example.admincf.lx.service.ActivtiyCfService;
import com.example.admincf.lx.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
* @Title: ＡctivtiyControl.java  
* @Package com.lx.control.cf  
* @Description: cf活动控制访问层
* @author lbk  
* @date 2019年4月28日 下午7:17:03  
* @version V1.0
 */
@Controller
@RequestMapping("/act")
@Slf4j
public class ActivtiyControl {
	@Autowired
	private ActivtiyCfService activtiyCfService;
	
	@RequestMapping("/actCfView")
	public ModelAndView actCfView(){
		ModelAndView mod=new ModelAndView();
		mod.setViewName("/act/cf-list");
		return mod;
	}
	
	@RequestMapping("getActivtyList")
	@ResponseBody
	public Result getActivtyList(ActivtiyCfVo vo){
		try {
			return new Result(true,"查询成功","0",this.activtiyCfService.getlistAll(vo));
		} catch (Exception ex) {
			log.error("查询失败，系统异常", ex);
			return new Result(true,"查询失败，系统异常","002",null);
		}
	}
}
