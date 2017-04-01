package org.engdream.base.web.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.engdream.base.entity.BaseEntity;
import org.engdream.base.entity.DataTable;
import org.engdream.base.service.BaseService;
import org.engdream.base.web.annotation.SearchParam;
import org.engdream.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 基础增删改查controller
 * @author yx
 *
 * @param <M>
 * @param <ID>
 */
public abstract class BaseCRUDController<M extends BaseEntity<ID>, ID extends Serializable> extends BaseController<M, ID> {
	public static final String OPERATOR = "operator";
	@Autowired
	protected BaseService<M, ID> baseService;
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreateForm(Model model){
		assertPermission(PERMS_CREATE);
		model.addAttribute("m", newModel());
		model.addAttribute(OPERATOR, "create");
		return viewName("edit");
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String showEditForm(Model model, @RequestParam("id")ID id){
		assertPermission(PERMS_VIEW);
		M m = baseService.selectById(id);
		model.addAttribute("m", m);
		model.addAttribute(OPERATOR, "update");
		return viewName("edit");
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<String> create(@Valid M m, BindingResult bindResult){
		assertPermission(PERMS_CREATE);
		Assert.notNull(m);
		if(bindResult.hasErrors()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
		}
		baseService.insert(m);
		return ResponseEntity.ok("创建成功");
	}
	
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@Valid M m, BindingResult bindResult){
		assertPermission(PERMS_UPDATE);
		Assert.notNull(m);
		if(bindResult.hasErrors()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
		}
		baseService.updateById(m);
		return ResponseEntity.ok("修改成功");
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<DataTable<M>> list(@SearchParam Page<M> page){
		assertPermission(PERMS_VIEW);
		DataTable<M> table =  new DataTable<>(baseService.selectPage(page));
		return ResponseEntity.ok(table);
	}
	
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public ResponseEntity<List<M>> all(){
		assertPermission(PERMS_VIEW);
		List<M> list =  baseService.selectList(null);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(ID id){
		assertPermission(PERMS_DELETE);
		Assert.notNull(id);
		baseService.deleteById(id);
		return ResponseEntity.ok("删除成功");
	}
	
	@RequestMapping(value = "batchDelete", method = RequestMethod.DELETE)
	public ResponseEntity<String> batchDelete(ID[] ids){
		assertPermission(PERMS_DELETE);
		baseService.deleteBatchIds(Arrays.asList(ids));
		return ResponseEntity.ok("批量删除成功");
	}
	
}
