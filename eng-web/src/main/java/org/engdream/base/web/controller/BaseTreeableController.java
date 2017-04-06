package org.engdream.base.web.controller;

import com.google.common.collect.Lists;

import org.engdream.base.entity.BaseEntity;
import org.engdream.base.entity.Treeable;
import org.engdream.base.service.BaseTreeableService;
import org.engdream.base.web.entity.ZTree;
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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
		assertPermission(PERMS_VIEW);
		setCommonDate(model);
		M m = baseTreeableService.selectById(id);
		model.addAttribute("m", m);
		return viewName("edit");
	}
    @RequestMapping(value = "update", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid M m, BindingResult bindResult){
		assertPermission(PERMS_UPDATE);
		Assert.notNull(m);
		if(bindResult.hasErrors()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
		}
		m.setModifiedTime(new Date());
		baseTreeableService.updateById(m);
		return ResponseEntity.ok("修改成功");
	}
    protected List<ZTree<ID>> convertToZtreeList(List<M> models, boolean async, boolean onlySelectLeaf) {
        List<ZTree<ID>> zTrees = Lists.newArrayList();

        if (models == null || models.isEmpty()) {
            return zTrees;
        }

        for (M m : models) {
            ZTree<ID> zTree = convertToZtree(m, !async, onlySelectLeaf);
            zTrees.add(zTree);
        }
        return zTrees;
    }
    
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<M> get(ID id) {
    	M m = baseTreeableService.selectById(id);
    	if(m == null){
    		return new ResponseEntity<>(m, HttpStatus.NO_CONTENT);
    	}
        return ResponseEntity.ok(m);
    }

    @RequestMapping(value = "appendChild", method = RequestMethod.POST)
    public ResponseEntity<M> appendChild(ID parentId) {
        M child = null;
        M parent = baseTreeableService.selectById(parentId);
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
    	M m = baseTreeableService.selectById(id);
    	Assert.notNull(m);
        baseTreeableService.deleteSelfAndChild(m);
        return ResponseEntity.ok("删除成功");
    }

    @RequestMapping("tree")
    public ResponseEntity<List<ZTree<ID>>> tree(){
        List<M> list = baseTreeableService.selectList(null);
        return ResponseEntity.ok(convertToZtreeList(list, true, true));
    }
    
    protected ZTree<ID> convertToZtree(M m, boolean open, boolean onlyCheckLeaf) {
        ZTree<ID> zTree = new ZTree<>();
        zTree.setId(m.getId());
        zTree.setPId(m.getParentId());
        zTree.setName(m.getName());
        zTree.setIconSkin(m.getIcon());
        zTree.setOpen(open);
        zTree.setRoot(m.isRoot());
        zTree.setRoot(m.getParentId().equals(0));
        if(baseTreeableService.hasChildren(m.getId())){
            zTree.setIsParent(true);
        }
        if (onlyCheckLeaf && zTree.isParent()) {
            zTree.setNocheck(true);
        } else {
            zTree.setNocheck(false);
        }

        return zTree;
    }
}



