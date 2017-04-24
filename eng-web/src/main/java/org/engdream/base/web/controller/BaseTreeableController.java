package org.engdream.base.web.controller;

import com.google.common.collect.Lists;
import org.engdream.base.entity.BaseEntity;
import org.engdream.base.entity.Treeable;
import org.engdream.base.service.BaseTreeableService;
import org.engdream.base.web.entity.ZTree;
import org.engdream.base.web.enums.PermissionEnum;
import org.engdream.common.util.LogUtil;
import org.engdream.common.util.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class BaseTreeableController<M extends BaseEntity<ID> & Treeable<ID>, ID extends Serializable> extends BaseController<M, ID>{
    @Autowired
	protected BaseTreeableService<M, ID> baseTreeableService;
    protected Class<M> clazz;
    
    protected BaseTreeableController() {
        this.clazz = ReflectUtils.findParameterizedType(getClass(), 0);
    }
    
    @RequestMapping("page/tree")
    public String treeForm(){
    	return viewName("tree");
    }
    
    @RequestMapping(value = "page/edit", method = RequestMethod.GET)
	public String showEditForm(Model model, @RequestParam("id")ID id){
		assertPermission(PermissionEnum.update.name());
		setCommonDate(model);
		M m = baseTreeableService.findById(id);
		model.addAttribute("m", m);
		return viewName("edit");
	}
    @RequestMapping(value = "update", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid M m, BindingResult bindResult){
		assertPermission(PermissionEnum.update.name());
		Assert.notNull(m, "entity must not be null");
		if(bindResult.hasErrors()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
		}
		m.setModifiedTime(new Date());
		baseTreeableService.updateById(m);
		return ResponseEntity.ok("修改成功");
	}

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<M> get(ID id) {
        assertPermission(PermissionEnum.view.name());
    	M m = baseTreeableService.findById(id);
    	if(m == null){
    		return new ResponseEntity<>(m, HttpStatus.NO_CONTENT);
    	}
        return ResponseEntity.ok(m);
    }

    @RequestMapping(value = "move", method = RequestMethod.GET)
    public ResponseEntity<Void> move(ID sourceId, ID targetId, String moveType) {
        assertPermission(PermissionEnum.view.name());
        M src = baseTreeableService.findById(sourceId);
        M target = baseTreeableService.findById(targetId);
        baseTreeableService.move(src, target, moveType);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "appendChild", method = RequestMethod.POST)
    public ResponseEntity<M> appendChild(ID parentId) {
        assertPermission(PermissionEnum.create.name());
        M child = null;
        M parent = baseTreeableService.findById(parentId);
        try {
            child = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LogUtil.e("", e);
        }
        child.setName("新节点");
        baseTreeableService.appendChild(parent, child);
        return ResponseEntity.ok(child);
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(ID id) {
    	assertPermission("delete");
    	M m = baseTreeableService.findById(id);
    	Assert.notNull(m, "entity must not be null");
        baseTreeableService.deleteSelfAndChild(m);
        return ResponseEntity.ok("删除成功");
    }

    @RequestMapping("tree")
    public ResponseEntity<List<ZTree<ID>>> tree(
            @RequestParam(required = false) ID[] checkIds,
            @RequestParam(required = false) boolean onlySelectLeaf) {
        assertPermission(PermissionEnum.view.name());
        List<M> list = baseTreeableService.findAll();
        return ResponseEntity.ok(convertToZtreeList(list, true, onlySelectLeaf, checkIds));
    }

    protected List<ZTree<ID>> convertToZtreeList(List<M> models, boolean async, boolean onlySelectLeaf, ID[] checkIds) {
        List<ZTree<ID>> zTrees = Lists.newArrayList();

        if (models == null || models.isEmpty()) {
            return zTrees;
        }

        for (M m : models) {
            boolean checked = false;
            if(checkIds != null){
                for(ID id : checkIds){
                    if(id == m.getId()){
                        checked = true;
                    }
                }
            }
            ZTree<ID> zTree = convertToZtree(m, !async, onlySelectLeaf, checked);
            zTrees.add(zTree);
        }
        return zTrees;
    }

    protected ZTree<ID> convertToZtree(M m, boolean open, boolean onlyCheckLeaf, boolean checked) {
        ZTree<ID> zTree = new ZTree<>();
        zTree.setId(m.getId());
        zTree.setPId(m.getParentId());
        zTree.setName(m.getName());
        zTree.setOpen(open);
        zTree.setRoot(m.isRoot());
        zTree.setRoot(m.getParentId().equals(0));
        zTree.setChecked(checked);
        if(baseTreeableService.hasChildren(m.getId())){
            zTree.setIsParent(true);
            zTree.setIconSkin(m.getBranchDefaultIcon());
        } else{
            zTree.setIconSkin(m.getLeafDefaultIcon());
        }
        if (onlyCheckLeaf && zTree.isParent()) {
            zTree.setNocheck(true);
        } else {
            zTree.setNocheck(false);
        }
        return zTree;
    }

}



