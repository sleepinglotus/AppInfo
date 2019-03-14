package cn.zzcai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzcai
 * @version 1.0
 * @date 2019年3月4日 下午4:32:37
 * @remark TODO
 */
@Controller
public class IndexController {
	
	@RequestMapping("/manager/login")
	public String managerLogin() {
		return "backendlogin";
	}
	
	@RequestMapping("/dev/login")
	public String devLogin() {
		return "devlogin";
	}
	
	/*@RequestMapping("/index")
	public String showIndex() {
		return "index.jsp";
	}*/
}
