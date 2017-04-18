package org.engdream.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.engdream.base.entity.BaseEntity;
/**
 * <p>
 * 
 * </p>
 *
 * @author Heyx
 * @since 2017-04-16
 */
@TableName("sys_permission")
public class Permission extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:权限名称;type:input;valid:required
     */
	private String name;
    /**
     * label:权限标识符;type:input;valid:required
     */
	private String permission;
    /**
     * label:描述;type:textarea
     */
	private String description;
    /**
     * label:是否显示;type:radio;valid:required
     */
	@TableField("is_show")
	private Boolean isShow;

	public String getName() {
        return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPermission() {
        return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getDescription() {
        return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsShow() {
        return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

}
