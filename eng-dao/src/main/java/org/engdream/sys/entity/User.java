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
 * @since 2017-03-30
 */
@TableName("sys_user")
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:用户名;type:hidden;
     */
	private String username;
	private String password;
	private String salt;
	@TableField("role_ids")
	private String roleIds;
	@TableField("is_locked")
	private Boolean isLocked;
	private String nickname;
	private String avatar;
	private String email;
	private String mobile;
	private String qq;
	private String wechat;
	@TableField("is_delete")
	private Boolean isDelete;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public Boolean isIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public Boolean isIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

}
