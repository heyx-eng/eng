package org.engdream.base.web.controller;

import org.engdream.base.web.annotation.CurrentUser;
import org.engdream.security.service.SecurityService;
import org.engdream.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("index")
	public String index(Model model, @CurrentUser User user){
		model.addAttribute("menu", securityService.findMenus(user));
		return "index";
	}

}
