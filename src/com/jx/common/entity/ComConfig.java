package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.DateUtil;
import com.jx.common.util.StringUtil;

public class ComConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop satrt********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 系统配置 主键id */
	private int configId;
	
	/** 配置类型 */
	private String configType;
		
	/** 配置名称 */
	private String configName;
		
	/** 接入网址 */
	private String param1;
		
	/** 端口号/数字 */
	private int param2;
		
	/** 账号/X */
	private String param3;
		
	/** 密码/Y */
	private String param4;
		
	/** 是否启动 */
	private String isOpen;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置 系统配置 主键id
	 * 
	 * @param int configId
	 */
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	
	/**
	 * 获取 系统配置 主键id
	 * 
	 * @return int configId
	 */
	public int getConfigId() {
		return this.configId;
	}
	
	/**
	 * 设置 配置类型
	 * 
	 * @param String configType
	 */
	public void setConfigType(String configType) {
		this.configType = StringUtil.trim(configType);
	}
	
	/**
	 * 获取 配置类型
	 * 
	 * @return String configType
	 */
	public String getConfigType() {
		return this.configType;
	}
	
	/**
	 * 设置 配置名称
	 * 
	 * @param String configName
	 */
	public void setConfigName(String configName) {
		this.configName = StringUtil.trim(configName);
	}
	
	/**
	 * 获取 配置名称
	 * 
	 * @return String configName
	 */
	public String getConfigName() {
		return this.configName;
	}
	
	/**
	 * 设置 接入网址
	 * 
	 * @param String param1
	 */
	public void setParam1(String param1) {
		this.param1 = StringUtil.trim(param1);
	}
	
	/**
	 * 获取 接入网址
	 * 
	 * @return String param1
	 */
	public String getParam1() {
		return this.param1;
	}
	
	/**
	 * 设置 端口号/数字
	 * 
	 * @param int param2
	 */
	public void setParam2(int param2) {
		this.param2 = param2;
	}
	
	/**
	 * 获取 端口号/数字
	 * 
	 * @return int param2
	 */
	public int getParam2() {
		return this.param2;
	}
	
	/**
	 * 设置 账号/X
	 * 
	 * @param String param3
	 */
	public void setParam3(String param3) {
		this.param3 = StringUtil.trim(param3);
	}
	
	/**
	 * 获取 账号/X
	 * 
	 * @return String param3
	 */
	public String getParam3() {
		return this.param3;
	}
	
	/**
	 * 设置 密码/Y
	 * 
	 * @param String param4
	 */
	public void setParam4(String param4) {
		this.param4 = StringUtil.trim(param4);
	}
	
	/**
	 * 获取 密码/Y
	 * 
	 * @return String param4
	 */
	public String getParam4() {
		return this.param4;
	}
	
	/**
	 * 设置 是否启动
	 * 
	 * @param String isOpen
	 */
	public void setIsOpen(String isOpen) {
		this.isOpen = StringUtil.trim(isOpen);
	}
	
	/**
	 * 获取 是否启动
	 * 
	 * @return String isOpen
	 */
	public String getIsOpen() {
		return this.isOpen;
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
		
	public void setModifyTimeStr(String modifyTimeStr) throws DataAccessException{
		modifyTimeStr = StringUtil.trim(modifyTimeStr);
		if(!modifyTimeStr.equals("")){
			try{
				setModifyTime(DateUtil.parseDate(modifyTimeStr));
			}catch(java.text.ParseException e){
				throw new com.cvicse.exception.DataAccessException(e);
			}
		}
	}

	public String getMeetingTimeStr(){
		return DateUtil.getFormatedDateString(getModifyTime());
	}	
	
	
	public ComConfig(){
		init();
	}
	
	public void init() {
		setConfigId(0);
	
		setConfigType("");
		setConfigName("");
		setParam1("");
		setParam2(0);
		setParam3("");
		setParam4("");
		setIsOpen("");
		setModifyTimeStr("1900-01-01");
	}
	/**************************table prop  end  *********************************/
}