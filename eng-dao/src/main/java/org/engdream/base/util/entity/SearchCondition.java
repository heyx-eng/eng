package org.engdream.base.util.entity;

import org.engdream.common.util.LogUtil;

public class SearchCondition {
	private String key;
	private Object value;
	private SearchOpt otp;
	private static final SearchOpt DEFAULT_OPT = SearchOpt.eq;
	
	public SearchCondition(String key, Object value) {
		this(key, value, DEFAULT_OPT);
	}
	public SearchCondition(String key, Object value, String optStr) {
		this.key = key;
		this.value = value;
		try{
			this.otp = SearchOpt.valueOf(optStr);
		}catch(Exception e){
			LogUtil.e("查询条件转换错误", e);
			this.otp = DEFAULT_OPT;
		}
	}
	public SearchCondition(String key, Object value, SearchOpt opt) {
		this.key = key;
		this.value = value;
		this.otp = opt;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public SearchOpt getOtp() {
		return otp;
	}
	public void setOtp(SearchOpt otp) {
		this.otp = otp;
	}
}
