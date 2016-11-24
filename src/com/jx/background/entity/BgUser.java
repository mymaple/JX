package com.jx.background.entity;

import com.jx.background.config.BgPage;

/**
 * 类名称：User.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2014年6月28日
 * @version 1.0
 */
public class BgUser {
	
	private String userId; // 用户id
	private String userName; // 用户名
	private String password; // 密码
	private String name; // 姓名
	private String rights; // 权限
	private String roleId; // 角色id
	private String lastLogin; // 最后登录时间
	private String loginIp; // 用户登录ip地址
	private String status; // 状态
	private String skin; // 皮肤
	
	private BgRole role; // 角色对象
	private BgPage page; // 分页对象
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public BgRole getRole() {
		return role;
	}
	public void setRole(BgRole role) {
		this.role = role;
	}
	public BgPage getPage() {
		return page;
	}
	public void setPage(BgPage page) {
		this.page = page;
	}
	

}
