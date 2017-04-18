package org.engdream.sys.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.sys.entity.Role;
import org.engdream.sys.entity.User;
import org.engdream.sys.service.RoleService;
import org.engdream.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private RoleService roleService;
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
		List<Role> roles = roleService.selectList(null);
        model.addAttribute("roles", roles);
        super.setCommonDate(model);
	}

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@Valid User user, BindingResult bindResult){
        assertPermission(PERMS_UPDATE);
        Assert.notNull(user);
        if(bindResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
        }
        if(StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(user.getPassword());
        }
        user.setModifiedTime(new Date());
        getUserService().updateById(user);
        return ResponseEntity.ok("修改成功");
    }
    @InitBinder("roles")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new CustomCollectionEditor(ArrayList.class, true));
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
