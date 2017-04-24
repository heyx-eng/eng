package org.engdream.sys.web.controller;

import org.engdream.base.web.controller.BaseTreeableController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.sys.entity.Resource;
import org.engdream.sys.service.PermissionService;
import org.engdream.sys.service.ResourceService;
import org.engdream.sys.web.enums.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
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
		model.addAttribute("permissionList", permissionService.findAll());
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
		Resource parent = getResourceService().findById(parentId);
		getResourceService().appendChild(parent, child);
		return ResponseEntity.ok(child);
	}
	@Override
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(Long id) {
		assertPermission("delete");
		Resource m = getResourceService().findById(id);
		Assert.notNull(m, "没有找到对应的实体");
		if(m.isRoot()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("根节点不能删除");
		}
		getResourceService().deleteSelfAndChild(m);
		return ResponseEntity.ok("删除成功");
	}

}
