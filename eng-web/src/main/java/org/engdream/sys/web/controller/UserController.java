package org.engdream.sys.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.base.web.enums.PermissionEnum;
import org.engdream.security.service.SecurityService;
import org.engdream.sys.entity.Role;
import org.engdream.sys.entity.User;
import org.engdream.sys.service.RoleService;
import org.engdream.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

    @Autowired
    private SecurityService securityService;

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
		List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        super.setCommonDate(model);
	}

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@Valid User m, BindingResult bindResult){
        assertPermission(PermissionEnum.create.name());
        Assert.notNull(m, "user must not be null");
        if(bindResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
        }
        User user = getUserService().findByUsername(m.getUsername());
        if(user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户已存在");
        }

        securityService.encryptPassword(m);
        m.setCreateTime(new Date());
        m.setModifiedTime(new Date());
        getUserService().save(m);
        return ResponseEntity.ok("创建成功");
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@Valid User user, BindingResult bindResult){
        assertPermission(PermissionEnum.update.name());
        Assert.notNull(user, "user must not be null");
        if(bindResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
        }
        if(StringUtils.isNotEmpty(user.getPassword())){
            securityService.encryptPassword(user);
        }
        user.setModifiedTime(new Date());
        getUserService().updateById(user);
        return ResponseEntity.ok("修改成功");
    }

    @Override
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(Long id){
        assertPermission(PermissionEnum.delete.name());
        getUserService().markDelete(id);
        return ResponseEntity.ok("删除成功");
    }
    @Override
    @RequestMapping(value = "batchDelete", method = RequestMethod.DELETE)
    public ResponseEntity<String> batchDelete(Long[] ids){
        assertPermission(PermissionEnum.delete.name());
        for(Long id : ids){
            getUserService().markDelete(id);
        }
        return ResponseEntity.ok("批量删除成功");
    }
}
