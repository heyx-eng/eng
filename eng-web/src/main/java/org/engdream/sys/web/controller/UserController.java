package org.engdream.sys.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baomidou.mybatisplus.plugins.Page;

import org.engdream.base.entity.DataTable;
import org.engdream.base.web.controller.BaseCRUDController;
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
public class UserController extends BaseCRUDController<User, Long> {
	@Override
	protected String getResourcePrefix() {
		return "sys:user";
	}
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<DataTable<User>> list(Page<User> page){
		assertPermission(PERMS_VIEW);
		DataTable<User> table =  new DataTable<>(baseService.selectPage(page));
		return ResponseEntity.ok(table);
	}
	
}
