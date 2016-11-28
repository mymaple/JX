package com.jx.background.entity;

import java.util.List;

/**
 * 类名称：Menu.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2014年6月28日
 * @version 1.0
 */
public class BgMenu {
	
	private String menuId;
	private String menuName;
	private String menuUrl;
	private String parentId;
	private String menuOrder;
	private String menuIcon;
	private String menuType;
	private String target;

	private BgMenu parentBgMenu;
	private List<BgMenu> subBgMenuList;

	private boolean hasMenu = false;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public BgMenu getParentBgMenu() {
		return parentBgMenu;
	}

	public void setParentBgMenu(BgMenu parentBgMenu) {
		this.parentBgMenu = parentBgMenu;
	}

	public List<BgMenu> getSubBgMenuList() {
		return subBgMenuList;
	}

	public void setSubBgMenuList(List<BgMenu> subBgMenuList) {
		this.subBgMenuList = subBgMenuList;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
	
}
