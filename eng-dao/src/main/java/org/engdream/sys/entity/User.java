package org.engdream.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.engdream.base.entity.BaseEntity;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Heyx
 * @since 2017-04-03
 */
@TableName("sys_user")
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:用户名;type:input;valid:required
     */
	private String username;
    /**
     * label:昵称;type:input;valid:required
     */
	private String nickname;
    /**
     * label:头像;type:image;valid:required
     */
	private String avatar;
    /**
     * label:密码;type:password;valid:required
     */
	private String password;
    /**
     * type:hidden
     */
	private String salt;
    /**
     * label:角色;type:muilty-select;valid:required
     */
	@TableField("role_ids")
	private String roleIds;
    /**
     * label:是否锁定;type:radio;valid:required
     */
	private Boolean locked;
    /**
     * label:是否删除;type:radio;valid:required
     */
	private Boolean deleted;
    /**
     * label:邮箱;type:input;valid:required
     */
	private String email;
    /**
     * label:手机;type:input;valid:mobile
     */
	private String mobile;
    /**
     * label:QQ;type:input
     */
	private String qq;
    /**
     * label:微信;type:input
     */
	private String wechat;

	public String getUsername() {
        return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	public Boolean getLocked() {
        return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean getDeleted() {
        return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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

}
