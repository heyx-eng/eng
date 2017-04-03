package org.engdream.base.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.engdream.base.entity.BaseEntity;
import org.engdream.base.service.BaseService;
import org.engdream.base.web.annotation.SearchParam;
import org.engdream.base.web.entity.DataTable;
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
import java.util.*;

/**
 * 基础增删改查controller
 * @author yx
 *
 * @param <M>
 * @param <ID>
 */
public abstract class BaseCRUDController<M extends BaseEntity<ID>, ID extends Serializable>
		extends BaseController<M, ID> {

	public static final String OPERATOR = "operator";
	@Autowired
	protected BaseService<M, ID> baseService;

	@RequestMapping(value = "page/create", method = RequestMethod.GET)
	public String showCreateForm(Model model){
		assertPermission(PERMS_CREATE);
		setCommonDate(model);
		model.addAttribute("m", newModel());
		model.addAttribute(OPERATOR, "create");
		return viewName("edit");
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<String> create(@Valid M m, BindingResult bindResult){
		assertPermission(PERMS_CREATE);
		Assert.notNull(m);
		if(bindResult.hasErrors()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求参数错误");
		}
		m.setCreateTime(new Date());
		m.setModifiedTime(new Date());
		baseService.insert(m);
		return ResponseEntity.ok("创建成功");
	}

	@RequestMapping(value = "page/edit", method = RequestMethod.GET)
	public String showEditForm(Model model, @RequestParam("id")ID id){
		assertPermission(PERMS_VIEW);
		setCommonDate(model);
		M m = baseService.selectById(id);
		model.addAttribute("m", m);
		Map<String, String> test = new HashMap<>();
		test.put("true", "是");
		test.put("false", "否");
		model.addAttribute("test", test);
		model.addAttribute(OPERATOR, "update");
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
		baseService.updateById(m);
		return ResponseEntity.ok("修改成功");
	}

	@RequestMapping(value = "page/list", method = RequestMethod.GET)
	public String listPage(Model model){
		assertPermission(PERMS_VIEW);
		setCommonDate(model);
		return viewName("list");
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
