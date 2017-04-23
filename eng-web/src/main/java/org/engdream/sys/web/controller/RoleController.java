package org.engdream.sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.sys.entity.Role;
import org.engdream.sys.service.RoleService;
/**
 * <p>
 * 
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@Controller
@RequestMapping("sys/role")
public class RoleController extends BaseCRUDController<Role, Long> {

    private RoleService getRoleService(){
        return (RoleService)baseService;
    }
	@Override
	protected String getResourcePrefix() {
		return "sys:role";
	}
	
	@Override
	protected void setCommonDate(Model model) {
		model.addAttribute("booleanList", BooleanEnum.values());
		super.setCommonDate(model);
	}

    @Override
    protected void setDefaultValue(Role role) {
        role.setDeleted(true);
    }

    @Override
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(Long id){
        assertPermission(PERMS_DELETE);
        getRoleService().markDelete(id);
        return ResponseEntity.ok("删除成功");
    }
    @Override
    @RequestMapping(value = "batchDelete", method = RequestMethod.DELETE)
    public ResponseEntity<String> batchDelete(Long[] ids){
        assertPermission(PERMS_DELETE);
        for(Long id : ids){
            getRoleService().markDelete(id);
        }
        return ResponseEntity.ok("批量删除成功");
    }
}
