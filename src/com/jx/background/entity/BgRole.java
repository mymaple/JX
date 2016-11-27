package com.jx.background.entity;

/**
 * 类名称：Role.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2014年3月10日
 * @version 1.0
 */
public class BgRole {
	
	private String roleId;
	private String roleName;
	private String permissions;
	private String parentId;
	private String addPermission;
	private String delPermission;
	private String editPermission;
	private String findPermission;
	private String permissionId;
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getAddPermission() {
		return addPermission;
	}
	public void setAddPermission(String addPermission) {
		this.addPermission = addPermission;
	}
	public String getDelPermission() {
		return delPermission;
	}
	public void setDelPermission(String delPermission) {
		this.delPermission = delPermission;
	}
	public String getEditPermission() {
		return editPermission;
	}
	public void setEditPermission(String editPermission) {
		this.editPermission = editPermission;
	}
	public String getFindPermission() {
		return findPermission;
	}
	public void setFindPermission(String findPermission) {
		this.findPermission = findPermission;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
