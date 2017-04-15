package org.engdream.sys.web.enums;

public enum ResourceType {
	MENU("菜单"),
	BUTTON("按钮");
	private String info;
	
	ResourceType(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	
	
}
