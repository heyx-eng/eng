package org.engdream.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.engdream.base.entity.BaseEntity;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@TableName("sys_role")
public class Role extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:角色;type:input;valid:required
     */
	private String role;
    /**
     * label:描述;type:textarea
     */
	private String description;
    /**
     * label:资源;type:tree
     */
	@TableField(value="resource_ids", el = "resourceIds,typeHandler=org.engdream.base.type.String2LongHandler")
	private List<Long> resourceIds;
    /**
     * label:是否可用;type:radio;valid:required
     */
	private Boolean available;

	public String getRole() {
        return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
        return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Boolean getAvailable() {
        return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
