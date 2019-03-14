package cn.zzcai.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.zzcai.pojo.DevUser;
import cn.zzcai.service.DevUserService;

@Controller
@RequestMapping("/dev")
public class DevUserController {
	
	@Resource
	private DevUserService devUserService;
	
	//登录
	@RequestMapping("/dologin")
	public String doLogin(
			@RequestParam
			String devCode,
			@RequestParam
			String devPassword,
			HttpSession session,
			Model model) {
		DevUser devUser=devUserService.doLogin(devCode, devPassword) ;
		if(devUser==null) {
			model.addAttribute("error","用户名或密码错误");
			return "devlogin";
		}else {
			session.setAttribute("devUserSession", devUser);
			return "developer/main";
		}
	}
	
	//注销
	@RequestMapping("/logout")
	public String doLogout(HttpSession session){
		session.removeAttribute("devUserSession");
		return "devlogin";
	}
}
