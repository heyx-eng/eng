package org.engdream.sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.sys.entity.User;
import org.engdream.sys.service.UserService;
/**
 * <p>
 * 
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@Controller
@RequestMapping("sys/user")
public class UserController extends BaseCRUDController<User, Long> {

    private UserService getUserService(){
        return (UserService)baseService;
    }
	@Override
	protected String getResourcePrefix() {
		return "sys:user";
	}
	@Override
	protected void setCommonDate(Model model) {
		model.addAttribute("booleanList", BooleanEnum.values());
		Map<String, String> test = new HashMap<>();
		test.put("true", "是");
		test.put("false", "否");
		model.addAttribute("roles", BooleanEnum.values());
		super.setCommonDate(model);
	}
    @Override
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(Long id){
        assertPermission(PERMS_DELETE);
        getUserService().markDelete(id);
        return ResponseEntity.ok("删除成功");
    }
    @Override
    @RequestMapping(value = "batchDelete", method = RequestMethod.DELETE)
    public ResponseEntity<String> batchDelete(Long[] ids){
        assertPermission(PERMS_DELETE);
        for(Long id : ids){
            getUserService().markDelete(id);
        }
        return ResponseEntity.ok("批量删除成功");
    }
}
