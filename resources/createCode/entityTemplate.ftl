package com.jx.${objectModuleNL}.entity;

public class ${objectModuleEU}${objectNameU} {
	
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
	public void set${objectNameN}Id(int ${objectNameL}Id) {
		this.${objectNameL}Id = ${objectNameL}Id;
	}
	
	/**
	 * 获取 ${tableName} 主键id
	 * 
	 * @return int ${objectNameL}Id
	 */
	public int get${objectNameN}Id() {
		return ${objectNameL}Id;
	}
	
	<#list fieldList as var>
		<#if var[3] == 'propType_String'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param String ${var[1]}
	 */
	public void set${var[0]}(String ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return String ${var[1]}
	 */
	public void get${var[0]}(String ${var[1]}) {
		return ${var[1]};
	}
		
		
		
		
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
	/**************************table prop  end  *********************************/
}