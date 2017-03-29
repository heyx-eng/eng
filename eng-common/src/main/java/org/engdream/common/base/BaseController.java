package org.engdream.common.base;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;

public class BaseController {
	public String showCreateForm(){
		return "edit";
	}
	public String showEditForm(){
		return "edit";
	}
	public String showViewForm(){
		return "edit";
	}
	public String get(){
		return "edit";
	}
	public ResponseEntity<ModelMap> create(){
		return null;
	}
	public String update(){
		return "create";
	}
	public String delete(){
		return "create";
	}
}
