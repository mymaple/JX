<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectModuleEU}${objectNameU}Mapper">

	<sql id="${objectNameL}AddColumns"><#list fieldList as var>${var[1]}<#if var_has_next>,</#if></#list></sql>
	<sql id="${objectNameL}Columns">${objectNameL}Id,<#list fieldList as var>${var[1]}<#if var_has_next>,</#if></#list></sql>
	
	<resultMap type="${objectModuleEL}${objectNameU}" id="${objectNameU}ResultMap">
		<id column="${objectNameL}Id" property="${objectNameL}Id"/>
			<#list fieldList as var>
		<result column="${var[1]}" property="${var[1]}"/>
			</#list>
	</resultMap>
	
	
	<!-- ****************************custom * start*********************************** -->
	
	
	<!-- ****************************custom * end  *********************************** -->
	
	
	<!-- ****************************common * satrt*********************************** -->
	<!-- 新增 -->
	<insert id="add" useGeneratedKeys="true" keyProperty="${objectNameL}Id" parameterType="${objectModuleEL}${objectNameU}">
		insert into ${objectModuleEL}${objectNameU} ( 
			<include refid="${objectNameL}AddColumns"/>
		) values (
			<#list fieldList as var>${r"#{"}${var[1]}${r"}"}<#if var_has_next>,</#if></#list>
		)
	</insert>
	
	<!-- 新增 -->
	<insert id="addByPd" useGeneratedKeys="true" keyProperty="${objectNameL}Id" parameterType="pd">
		insert into ${objectModuleEL}${objectNameU} ( 
			<include refid="${objectNameL}AddColumns"/>
		) values (
			<#list fieldList as var>${r"#{"}${var[1]}${r"}"}<#if var_has_next>,</#if></#list>
		)
	</insert>
	
	<!-- 修改 -->
	<update id="edit" parameterType="${objectModuleEL}${objectNameU}">
		update  ${objectModuleEL}${objectNameU}
			set 
				<#list fieldList as var>
				${var[1]} = ${r"#{"}${var[1]}${r"}"}<#if var_has_next>,</#if>
				</#list>
			where 
				${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</update>
	
	<!-- 修改 -->
	<update id="editByPd" parameterType="pd">
		update  ${objectModuleEL}${objectNameU}
			set 
				<#list fieldList as var>
				${var[1]} = ${r"#{"}${var[1]}${r"}"}<#if var_has_next>,</#if>
				</#list>
			where 
				${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</update>
	
	<!-- 删除 -->
	<delete id="deleteById" parameterType="int">
		delete from ${objectModuleEL}${objectNameU} 
		where 
			${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</delete>
	
	<!-- 删除 -->
	<delete id="deleteByPd" parameterType="pd">
		delete from ${objectModuleEL}${objectNameU} 
		where 
			${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</delete>
	
	<!-- 批量删除 -->
	<delete id="batchDeleteByIds" parameterType="String">
		delete from comTable 
		where 
			tableId in (${r"#{"}ids${r"}"})
	</delete>
	
	<!-- 通过id获取(类)数据 -->
	<select id="findById" parameterType="int" resultType="${objectModuleEL}${objectNameU}">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
		where 
			${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="findPdById" parameterType="int" resultType="pd">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
		where 
			${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</select>
	
	<!-- 通过pd获取(PageData)数据  -->
	<select id="findPdByPd" parameterType="pd" resultType="pd">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
		where 
			${objectNameL}Id = ${r"#{"}${objectNameL}Id${r"}"}
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="listAllByPd" parameterType="pd" resultMap="${objectNameU}ResultMap">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="listAllPd" parameterType="${controlModuleEL}Page" resultType="pd">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
	</select>
	
	<!-- ****************************common * end  ********************************** -->
	
</mapper>