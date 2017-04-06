package org.engdream.sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import org.engdream.base.web.controller.BaseTreeableController;
import org.engdream.base.web.enums.BooleanEnum;
import org.engdream.sys.entity.Resource;
import org.engdream.sys.service.ResourceService;
import org.engdream.sys.web.enums.ResourceType;
/**
 * <p>
 * 资源
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@Controller
@RequestMapping("sys/resource")
public class ResourceController extends BaseTreeableController<Resource, Long> {

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
		super.setCommonDate(model);
	}
	
	@RequestMapping(value = "appendChild", method = RequestMethod.POST)
    public ResponseEntity<Resource> appendChild(Long parentId) {
		Resource child = new Resource();
		Resource parent = baseTreeableService.selectById(parentId);
        child.setName("新节点");
        child.setType(ResourceType.MENU.getInfo());
        child.setAvailable(true);
        child.setCreateTime(new Date());
        child.setModifiedTime(new Date());
        child.setPermission("1,2,3,");
        baseTreeableService.appendChild(parent, child);
        return ResponseEntity.ok(child);
    }
}
