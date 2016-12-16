package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jx.common.util.DateUtil;
import com.jx.common.util.StringUtil;

public class BgMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**  */
	private String target;
	
	/** 上级菜单 */
	private BgMenu parentBgMenu;
	
	/** 子菜单列表 */
	private List<BgMenu> subBgMenuList;
	
	/** 是否有次菜单权限 */
	private boolean hasRight = false;
	
	
	/**
	 * 获取 
	 * 
	 * @return String target
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * 设置
	 * 
	 * @param String target
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	/**
	 * 获取 上级菜单
	 * 
	 * @return BgMenu parentBgMenu
	 */
	public BgMenu getParentBgMenu() {
		return this.parentBgMenu;
	}
	
	/**
	 * 设置 上级菜单
	 * 
	 * @param BgMenu parentBgMenu
	 */
	public void setParentBgMenu(BgMenu parentBgMenu) {
		this.parentBgMenu = parentBgMenu;
	}
	
	/**
	 * 获取 子菜单列表
	 * 
	 * @return List<BgMenu> subBgMenuList
	 */
	public List<BgMenu> getSubBgMenuList() {
		return this.subBgMenuList;
	}
	
	/**
	 * 设置 子菜单列表
	 * 
	 * @param List<BgMenu> subBgMenuList
	 */
	public void setSubBgMenuList(List<BgMenu> subBgMenuList) {
		this.subBgMenuList = subBgMenuList;
	}
	
	/**
	 * 获取 是否有此菜单 
	 * 1是,0否
	 * 
	 * @return boolean hasRight
	 */
	public boolean isHasRight() {
		return this.hasRight;
	}
	
	/**
	 * 设置 是否有此菜单 
	 * 1是,0否
	 * 
	 * @param boolean hasRight
	 */
	public void setHasRight(boolean hasRight) {
		this.hasRight = hasRight;
	}
	
	/**************************custom prop end**********************************/
	
	
	/**************************table prop satrt*********************************/
	
	

	/** 菜单表 主键id */
	private int menuId;
	
	/** 菜单名 */
	private String menuName;
		
	/** 菜单链接 */
	private String menuUrl;
		
	/** 上级菜单id */
	private int parentId;
		
	/** 本级菜单序号 */
	private int menuOrder;
		
	/** 菜单图标 */
	private String menuIcon;
		
	/** 菜单类型 */
	private String menuType;
		
	/** 状态 */
	private String status;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置 菜单表 主键id
	 * 
	 * @param int menuId
	 */
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
	/**
	 * 获取 菜单表 主键id
	 * 
	 * @return int menuId
	 */
	public int getMenuId() {
		return this.menuId;
	}
	
	/**
	 * 设置 菜单名
	 * 
	 * @param String menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = StringUtil.trim(menuName);
	}
	
	/**
	 * 获取 菜单名
	 * 
	 * @return String menuName
	 */
	public String getMenuName() {
		return this.menuName;
	}
	
	/**
	 * 设置 菜单链接
	 * 
	 * @param String menuUrl
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = StringUtil.trim(menuUrl);
	}
	
	/**
	 * 获取 菜单链接
	 * 
	 * @return String menuUrl
	 */
	public String getMenuUrl() {
		return this.menuUrl;
	}
	
	/**
	 * 设置 上级菜单id
	 * 
	 * @param int parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取 上级菜单id
	 * 
	 * @return int parentId
	 */
	public int getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置 本级菜单序号
	 * 
	 * @param int menuOrder
	 */
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	
	/**
	 * 获取 本级菜单序号
	 * 
	 * @return int menuOrder
	 */
	public int getMenuOrder() {
		return this.menuOrder;
	}
	
	/**
	 * 设置 菜单图标
	 * 
	 * @param String menuIcon
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = StringUtil.trim(menuIcon);
	}
	
	/**
	 * 获取 菜单图标
	 * 
	 * @return String menuIcon
	 */
	public String getMenuIcon() {
		return this.menuIcon;
	}
	
	/**
	 * 设置 菜单类型
	 * 
	 * @param String menuType
	 */
	public void setMenuType(String menuType) {
		this.menuType = StringUtil.trim(menuType);
	}
	
	/**
	 * 获取 菜单类型
	 * 
	 * @return String menuType
	 */
	public String getMenuType() {
		return this.menuType;
	}
	
	/**
	 * 设置 状态
	 * 
	 * @param String status
	 */
	public void setStatus(String status) {
		this.status = StringUtil.trim(status);
	}
	
	/**
	 * 获取 状态
	 * 
	 * @return String status
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置 修改时间
	 * 
	 * @param Date modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	/**
	 * 获取 修改时间
	 * 
	 * @return Date modifyTime
	 */
	public Date getModifyTime() {
		return this.modifyTime;
	}	
		
	public void setModifyTimeStr(String modifyTimeStr) throws Exception{
		modifyTimeStr = StringUtil.trim(modifyTimeStr);
		if(!modifyTimeStr.equals("")){
			try{
				setModifyTime(DateUtil.parseDate(modifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getModifyTimeStr(){
		return DateUtil.getFormatedDateString(getModifyTime());
	}	
	
	
	public BgMenu(){
		init();
	}
	
	public void init() {
		setMenuId(0);
	
		setMenuName("");
		setMenuUrl("");
		setParentId(0);
		setMenuOrder(0);
		setMenuIcon("");
		setMenuType("");
		setStatus("");
		try {
			setModifyTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**************************table prop  end  *********************************/
}