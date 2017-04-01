package org.engdream.base.util.entity;

public enum SearchOpt {
	eq("相等"),
	ne("不等"),
	gt("大于"),
	ge("大于等于"),
	lt("小于"),
	le("小于等于"),
	like("全模糊"),
	likeL("左前缀匹配"),
	likeR("右前缀匹配");
	private String info;
	
	SearchOpt(String info){
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
}
