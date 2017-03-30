package org.engdream.sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.engdream.base.web.controller.BaseController;
import org.engdream.sys.entity.User;
/**
 * <p>
 * 
 * </p>
 *
 * @author Heyx
 * @since 2017-03-30
 */
@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController<User, Long> {
	@Override
	protected String getResourcePrefix() {
		return "sys:user";
	}
}
