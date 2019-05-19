package com.example.admincf.lx.control.inter;

import com.example.admincf.lx.pojo.po.HttpParm;
import com.example.admincf.lx.util.HttpUtil;
import com.example.admincf.lx.util.LxUtil;
import com.example.admincf.lx.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 
* 项目名称：admin   
* 类名称：InterControl   
* 类描述：接口管理控制类
* 创建人：Administrator   
* 创建时间：2019年4月26日 上午11:23:02   
* 修改人：Administrator   
* 修改时间：2019年4月26日 上午11:23:02   
* 修改备注： 
* @version 1.0
 */
@Controller
@RequestMapping("/inter")
@Slf4j
public class InterControl {

	@RequestMapping("/interview")
	public ModelAndView interview(){
		ModelAndView mod=new ModelAndView();
		mod.setViewName("/inter/index");
		return mod;
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/gethttputil")
	@ResponseBody
	public Result getHttpUtil(HttpParm parm){
		try {
			if(parm==null){
				return new Result(false,"查询失败,请检查参数是否合法","999",null);
			}
			if(!LxUtil.isEmpty(parm.getUrl())){
				return new Result(false,"查询失败,请检查参数是否完整","999",null);
			}
			if(!parm.getCsfs().equals("post")&&!parm.getCsfs().equals("get")){
				return new Result(false,"查询失败,请检查传输方式是否规范","999",null);
			}
			//所有判断条件逻辑成立，进行业务逻辑处理
			Map<String,String> map=LxUtil.parmUtil(parm.getKeyparm(), parm.getVueparm());//获取参数
			if(parm.getCsfs().equals("post")){//post
				if(parm.getCsgs().equals("json")){//json
					if(LxUtil.isEmpty(parm.getCookieparm())){
						return new Result(true,"查询成功","000", HttpUtil.httpPostConnection(parm.getUrl(), HttpUtil.jsonParmUtil(map),parm.getCookieparm()));
					}
					return new Result(true,"查询成功","000",HttpUtil.httpPostConnection(parm.getUrl(), HttpUtil.jsonParmUtil(map)));
				}else if(parm.getCsgs().equals("x-www-form-urlencoded")){//表单
					if(LxUtil.isEmpty(parm.getCookieparm())){
						return new Result(true,"查询成功","000",HttpUtil.httpPostConnection(parm.getUrl(), HttpUtil.nameParmUtil(map),parm.getCookieparm()));
					}
					return new Result(true,"查询成功","000",HttpUtil.httpPostConnection(parm.getUrl(), HttpUtil.nameParmUtil(map)));
				}
			}else{//get
					return new Result(true,"查询成功","000",HttpUtil.httpGetConnection(parm.getUrl(),HttpUtil.strParmUtil(map)));
			}
		} catch (Exception e) {
			log.error("请求远程出错",e);
			return new Result(false, "查询失败，系统异常", "999",null);
		}
		return new Result(false, "查询失败，系统异常", "999",null);
	}
}
