package org.engdream.base.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}

	@RequestMapping(value="login", method = RequestMethod.POST)
	public ResponseEntity<String> login(String username, String password){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
			return ResponseEntity.ok("登录成功");
		} catch (UnknownAccountException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户不存在");
		} catch (DisabledAccountException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户被锁定");
		} catch (AuthenticationException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账号验证失败");
		}
	}

}
