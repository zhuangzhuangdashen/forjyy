package com.example.admincf.lx.control.user;

import com.example.admincf.lx.pojo.po.User;
import com.example.admincf.lx.service.UserService;
import com.example.admincf.lx.util.LxUtil;
import com.example.admincf.lx.util.Result;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserControl {

	private static final Logger logger = Logger.getLogger(UserControl.class);

	@Resource
	private UserService userService;

	@RequestMapping("getuser")
	@ResponseBody
	public User getUser() {
		return this.userService.getByUserName("");
	}

	@RequestMapping("/login")
	@ResponseBody
	public Result login(User user) {
		Result result=null;
		try {
			if(!LxUtil.isEmpty(user.getUsername())&& LxUtil.isEmpty(user.getPwd())){
				result=new Result(true,"账号密码不能为","005");
				logger.error("======登陆失败，账号密码为空=======");
				return result;
			}
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPwd());
			subject.login(usernamePasswordToken);
			logger.info("======登陆成功=======");
			result=new Result(true, "登陆成功", "001");
		} catch (IncorrectCredentialsException e) {
			result=new Result(true, "密码错误", "006");
			logger.error("======密码错误=======");
			return result;
		} catch (AuthenticationException e) {
			result=new Result(true, "用户名错误", "007");
			logger.error("======用户名错误=======",e);
			return result;
		}
		return result;
	}

	@RequestMapping("/list")
	public ModelAndView list(User user) {
		ModelAndView mode=new ModelAndView();
		mode.setViewName("/list");
		return mode;
	}

}
