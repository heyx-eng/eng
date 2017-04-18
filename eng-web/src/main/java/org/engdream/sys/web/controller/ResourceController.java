package org.engdream.sys.web.controller;

import org.engdream.base.web.controller.BaseTreeableController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.sys.entity.Resource;
import org.engdream.sys.service.PermissionService;
import org.engdream.sys.service.ResourceService;
import org.engdream.sys.web.enums.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author Heyx
 * @since 2017-04-06
 */
@Controller
@RequestMapping("sys/resource")
public class ResourceController extends BaseTreeableController<Resource, Long> {
	@Autowired
	private PermissionService permissionService;
    private ResourceService getResourceService(){
    	return (ResourceService)baseTreeableService;
    }
	@Override
	protected String getResourcePrefix() {
		return "sys:resource";
	}
	
	@Override
	protected void setCommonDate(Model model) {
		model.addAttribute("booleanList", BooleanEnum.values());
		model.addAttribute("resourceTypeList", ResourceType.values());
		model.addAttribute("permissionList", permissionService.selectList(null));
		super.setCommonDate(model);
	}
	@RequestMapping(value = "appendChild", method = RequestMethod.POST)
	public ResponseEntity<Resource> appendChild(Long parentId) {
		Resource child = new Resource();
		child.setCreateTime(new Date());
		child.setModifiedTime(new Date());
		child.setType(ResourceType.MENU.getInfo());
		child.setAvailable(true);
		child.setIdentity("");
		child.setType(ResourceType.MENU.getInfo());
		child.setName("新节点");
		child.setCreateTime(new Date());
		child.setModifiedTime(new Date());
		Resource parent = getResourceService().selectById(parentId);
		getResourceService().appendChild(parent, child);
		return ResponseEntity.ok(child);
	}
}
