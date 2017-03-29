package org.eng.codegen.generator.config;

import java.util.HashMap;
import java.util.Map;

public class FreemarkerData {
	private Map<String, Object> data = new HashMap<>();
	
	public void put(String key, Object value){
		data.put(key, value);
	}
	
	public Object get(String key){
		return data.get(key);
	}

	public Map<String, Object> getData() {
		return data;
	}
	
}
