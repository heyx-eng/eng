package org.engdream.sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.sys.entity.User;
import org.engdream.sys.service.UserService;
/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Heyx
 * @since 2017-04-03
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
