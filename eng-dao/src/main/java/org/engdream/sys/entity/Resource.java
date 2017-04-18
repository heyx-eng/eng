package org.engdream.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.engdream.base.entity.BaseEntity;
import org.engdream.base.entity.Treeable;
/**
 * <p>
 * 资源
 * </p>
 *
 * @author Heyx
 * @since 2017-04-06
 */
@TableName("sys_resource")
public class Resource extends BaseEntity<Long> implements Treeable<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:资源名称;type:input;valid:required
     */
	private String name;
    /**
     * label:资源类型;type:select;valid:required
     */
	private String type;
	/**
	 * label:图标;type:input
	 */
	private String icon;
    /**
     * label:跳转链接;type:input
     */
	private String url;
    /**
     * label:权重;type:input
     */
	private Integer weight;
    /**
     * label:父节点;type:input
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * label:路径;type:input
     */
	@TableField("parent_ids")
	private String parentIds;
    /**
     * label:权限;type:select;valid:required
     */
	private String identity;
    /**
     * label:是否可用;type:radio;valid:required
     */
	private Boolean available;

	public String getName() {
        return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
        return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
        return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getWeight() {
        return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Long getParentId() {
        return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
        return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Boolean getAvailable() {
        return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	@Override
	public String getIcon() {
		return this.icon;
	}

	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String getSeparator() {
		return "/";
	}

	@Override
	public String makeSelfAsNewParentIds() {
		return getParentIds()+getId()+getSeparator();
	}

	@Override
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRootDefaultIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBranchDefaultIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLeafDefaultIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
