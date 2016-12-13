package com.jx.${objectModuleNL}.entity;

import java.io.Serializable;

import com.jx.common.util.StringUtil;

public class ${objectModuleEU}${objectNameU} implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop satrt********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** ${tableName} 主键id */
	private int ${objectNameL}Id;
	
	<#list fieldList as var>
	/** ${var[2]} */
		<#if var[3] == 'propType_String'>
	private String ${var[1]};
		<#elseif var[3] == 'propType_Int'>
	private int ${var[1]};
		<#elseif var[3] == 'propType_Date'>
	private java.util.Date ${var[1]};
		<#elseif var[3] == 'propType_Double'>
	private double ${var[1]};
		<#elseif var[3] == 'propType_Boolean'>
	private boolean ${var[1]};	
		</#if>
		
	</#list>
	
	
	/**
	 * 设置 ${tableName} 主键id
	 * 
	 * @param int ${objectNameL}Id
	 */
	public void set${objectNameU}Id(int ${objectNameL}Id) {
		this.${objectNameL}Id = ${objectNameL}Id;
	}
	
	/**
	 * 获取 ${tableName} 主键id
	 * 
	 * @return int ${objectNameL}Id
	 */
	public int get${objectNameU}Id() {
		return this.${objectNameL}Id;
	}
	
	<#list fieldList as var>
		<#if var[3] == 'propType_String'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param String ${var[1]}
	 */
	public void set${var[0]}(String ${var[1]}) {
		this.${var[1]} = StringUtil.trim(${var[1]});
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return String ${var[1]}
	 */
	public String get${var[0]}() {
		return this.${var[1]};
	}
		<#elseif var[3] == 'propType_Int'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param int ${var[1]}
	 */
	public void set${var[0]}(int ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return int ${var[1]}
	 */
	public int get${var[0]}() {
		return this.${var[1]};
	}
		<#elseif var[3] == 'propType_Date'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param Date ${var[1]}
	 */
	public void set${var[0]}(Date ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return Date ${var[1]}
	 */
	public Date get${var[0]}() {
		return this.${var[1]};
	}	
		
	public void set${var[0]}Str(String ${var[1]}Str) throws DataAccessException{
		${var[1]}Str = StringUtil.trim(${var[1]}Str);
		if(!${var[1]}Str.equals("")){
			try{
				set${var[0]}(DateUtil.parseDate(${var[1]}Str));
			}catch(java.text.ParseException e){
				throw new com.cvicse.exception.DataAccessException(e);
			}
		}
	}

	public String getMeetingTimeStr(){
		return DateUtil.getFormatedDateString(get${var[0]}());
	}	
		<#elseif var[3] == 'propType_Double'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param double ${var[1]}
	 */
	public void set${var[0]}(double ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return double ${var[1]}
	 */
	public double get${var[0]}() {
		return this.${var[1]};
	}
	
	public void set${var[0]}Str(String ${var[1]}Str){
		brateStr = com.cvicse.util.StringUtil.trim(${var[1]}Str);
		if(!${var[1]}Str.equals("")){
			set${var[0]}(DecimalUtil.strToDouble(${var[1]}Str));
		}
	}

	public String get${var[0]}Str(){
		return DecimalUtil.doubleToStr(get${var[0]});
	}	
		<#elseif var[3] == 'propType_Boolean'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param boolean ${var[1]}
	 */
	public void set${var[0]}(boolean ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return boolean ${var[1]}
	 */
	public boolean get${var[0]}() {
		return this.${var[1]};
	}
		</#if>
	
	</#list>
	
	public ${objectModuleEU}${objectNameU}(){
		init();
	}
	
	public void init() {
		set${objectNameU}Id(0);
	
	<#list fieldList as var>
		<#if var[3] == 'propType_String'>
		set${var[0]}("");
		<#elseif var[3] == 'propType_Int'>
		set${var[0]}(0);
		<#elseif var[3] == 'propType_Date'>
		set${var[0]}Str("1900-01-01");
		<#elseif var[3] == 'propType_Double'>
		set${var[0]}(0.00);
		<#elseif var[3] == 'propType_Boolean'>
		set${var[0]}(false);
		</#if>
	</#list>
	}
	/**************************table prop  end  *********************************/
}