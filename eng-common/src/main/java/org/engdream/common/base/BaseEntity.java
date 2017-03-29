package org.engdream.common.base;

import java.io.Serializable;

public class BaseEntity<ID extends Serializable> implements Serializable{
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
