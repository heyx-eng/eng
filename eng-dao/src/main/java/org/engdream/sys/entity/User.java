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
@TableName("sys_user")
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * label:用户名;type:input;valid:username
     */
	private String username;
    /**
     * label:密码;type:password;valid:pwd
     */
	private String password;
    /**
     * type:hidden
     */
	private String salt;
    /**
     * label:角色;type:checkbox;valid:required
     */
	@TableField(value="role_ids", el = "roleIds,typeHandler=org.engdream.base.type.String2LongHandler")
	private List<Long> roleIds;
    /**
     * label:是否锁定;type:radio;valid:required
     */
	private Boolean locked;
    /**
     * label:昵称;type:input
     */
	private String nickname;
    /**
     * label:头像;type:image;valid:required
     */
	private String avatar;
    /**
     * label:邮箱;type:input;valid:email
     */
	private String email;
    /**
     * label:手机;type:input;valid:phone
     */
	private String mobile;
    /**
     * label:QQ;type:input;valid:qq
     */
	private String qq;
    /**
     * label:微信;type:input
     */
	private String wechat;
    /**
     * label:是否删除;type:radio;valid:required
     */
	private Boolean deleted;

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

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Boolean getLocked() {
        return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
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
	public Boolean getDeleted() {
        return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


    public String getCredentialsSalt() {
        return getUsername()+getSalt();
    }
}
