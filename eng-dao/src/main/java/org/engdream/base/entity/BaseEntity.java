package org.engdream.base.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<ID extends Serializable> implements Serializable{
	@TableId(value="id", type= IdType.AUTO)
	private ID id;
	@TableField("create_time")
	private Date createTime;
	@TableField("modified_time")
	private Date modifiedTime;
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
