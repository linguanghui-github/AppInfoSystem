package com.project.controller;


import com.project.entity.BackendUser;
import com.project.service.BackendUserService;
import com.project.tools.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/backendmanager")
public class UserLoginController {

	@Resource
	private BackendUserService backendUserService;

	/**
	 * 跳转到后台管理者登录页面
	 * @return
	 */
	@RequestMapping("/backendlogin")
	public String tobackendlogin(){
		return "/backendlogin";
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session){
		//调用service方法，进行用户匹配
		BackendUser user = null;
		try {
			user = backendUserService.login(userCode,userPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != user){//登录成功
			//放入session
			session.setAttribute(Constants.USER_SESSION, user);
			//页面跳转（main.jsp）
			return "redirect:/backendmanager/backend/main";
		}else{
			//页面跳转（login.jsp）带出提示信息--转发
			request.setAttribute("error", "用户名或密码不正确");
			return "backendlogin";
		}
	}
	
	@RequestMapping(value="/backend/main")
	public String main(HttpSession session){
		if(session.getAttribute(Constants.USER_SESSION) == null){
			return "redirect:/backendmanager/login";
		}
		return "backend/main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		//清除session
		session.removeAttribute(Constants.USER_SESSION);
		return "backendlogin";
	}
}
