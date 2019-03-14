package cn.zzcai.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.zzcai.pojo.BackendUser;
import cn.zzcai.service.BackendUserService;

/**
 * @author zzcai
 * @version 1.0
 * @date 2019年3月5日 上午11:45:10
 * @remark TODO
 */
@Controller
@RequestMapping("/manager")
public class BackendUserController {
	@Resource
	private BackendUserService backendUserService;
	
	//后台登录
	@RequestMapping("/dologin")
	public String doLogin(
			@RequestParam
			String userCode,
			@RequestParam
			String userPassword,
			HttpSession session,
			Model model) {
		BackendUser backendUser=backendUserService.doLogin(userCode, userPassword) ;
		if(backendUser==null) {
			model.addAttribute("error","用户名或密码错误");
			return "backendlogin";
		}else {
			session.setAttribute("userSession", backendUser);
			return "backend/main";
		}
	}
	
	//注销
	@RequestMapping("/logout")
	public String doLogout(HttpSession session){
		session.removeAttribute("userSession");
		return "backendlogin";
	}
}
