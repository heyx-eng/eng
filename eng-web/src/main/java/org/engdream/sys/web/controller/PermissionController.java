package org.engdream.sys.web.controller;

import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.sys.entity.Permission;
import org.engdream.sys.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * <p>
 * 
 * </p>
 *
 * @author Heyx
 * @since 2017-04-16
 */
@Controller
@RequestMapping("sys/permission")
public class PermissionController extends BaseCRUDController<Permission, Long> {

    private PermissionService getPermissionService(){
        return (PermissionService)baseService;
    }
	@Override
	protected String getResourcePrefix() {
		return "sys:permission";
	}
	
	@Override
	protected void setCommonDate(Model model) {
		model.addAttribute("booleanList", BooleanEnum.values());
		super.setCommonDate(model);
	}
}
