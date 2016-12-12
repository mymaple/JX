<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectName}Mapper">
	
	
	<!-- 新增-->
	<insert id="save" parameterType="pd">
		insert into ${tabletop}${objectNameUpper}(
	<#list fieldList as var>
			${var[0]},	
	</#list>
			${objectNameUpper}_ID
		) values (
	<#list fieldList as var>
			${r"#{"}${var[0]}${r"}"},	
	</#list>
			${r"#{"}${objectNameUpper}_ID${r"}"}
		)
	</insert>
	
	
	<!-- 删除-->
	<delete id="delete" parameterType="pd">
		delete from ${tabletop}${objectNameUpper}
		where 
			${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</delete>
	
	
	<!-- 修改 -->
	<update id="edit" parameterType="pd">
		update  ${tabletop}${objectNameUpper}
			set 
	<#list fieldList as var>
		<#if var[3] == "是">
				${var[0]} = ${r"#{"}${var[0]}${r"}"},
		</#if>
	</#list>
			${objectNameUpper}_ID = ${objectNameUpper}_ID
			where 
				${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</update>
	
	
	<!-- 通过ID获取数据 -->
	<select id="findById" parameterType="pd" resultType="pd">
		select 
	<#list fieldList as var>
			${var[0]},	
	</#list>
			${objectNameUpper}_ID
		from 
			${tabletop}${objectNameUpper}
		where 
			${objectNameUpper}_ID = ${r"#{"}${objectNameUpper}_ID${r"}"}
	</select>
	
	
	<!-- 列表 -->
	<select id="datalistPage" parameterType="page" resultType="pd">
		select
		<#list fieldList as var>
				a.${var[0]},	
		</#list>
				a.${objectNameUpper}_ID
		from 
				${tabletop}${objectNameUpper} a
	</select>
	
	<!-- 列表(全部) -->
	<select id="listAll" parameterType="pd" resultType="pd">
		select
		<#list fieldList as var>
				a.${var[0]},	
		</#list>
				a.${objectNameUpper}_ID
		from 
				${tabletop}${objectNameUpper} a
	</select>
	
	<!-- 批量删除 -->
	<delete id="deleteAll" parameterType="String">
		delete from ${tabletop}${objectNameUpper}
		where 
			${objectNameUpper}_ID in
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
                 ${r"#{item}"}
		</foreach>
	</delete>
	
</mapper>

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectModuleEU}${objectNameU}Mapper">

	<sql id="${objectNameL}AddColumns"><#list fieldList as var>${var[1]}<#if word_has_next>,</#if></#list></sql>
	<sql id="${objectNameL}Columns">${objectNameL}Id,<#list fieldList as var>${var[1]}<#if word_has_next>,</#if></#list></sql>
	
	<resultMap type="${objectModuleEU}${objectNameU}" id="${objectNameU}ResultMap">
		<id column="${objectNameL}Id" property="${objectNameL}Id"/>
			<#list fieldList as var>
		<result column="${var[1]}" property="${var[1]}"/>
			</#list>
	</resultMap>
	
	<!-- ****************************common * satrt*********************************** -->
	<!-- 新增 -->
	<insert id="add" useGeneratedKeys="true" keyProperty="${objectNameL}Id" parameterType="${objectModuleEL}${objectNameU}">
		insert into ${objectModuleEL}${objectNameU} ( 
			<include refid="${objectNameL}AddColumns"/>
		) values (
			<#list fieldList as var>${r"#{"}${var[0]}${r"}"}<#if word_has_next>,</#if></#list>
		)
	</insert>
	
	<!-- 新增 -->
	<insert id="addByPd" useGeneratedKeys="true" keyProperty="${objectNameL}Id" parameterType="pd">
		insert into ${objectModuleEL}${objectNameU} ( 
			<include refid="${objectNameL}AddColumns"/>
		) values (
			<#list fieldList as var>${r"#{"}${var[0]}${r"}"}<#if word_has_next>,</#if></#list>
		)
	</insert>
	
	<!-- 修改 -->
	<update id="edit" parameterType="${objectModuleEL}${objectNameU}">
		update  ${objectModuleEL}${objectNameU}
			set 
				<#list fieldList as var>
				${var[0]} = ${r"#{"}${var[0]}${r"}"}<#if word_has_next>,</#if>
				</#list>
			where 
				${objectNameL}Id = #{${objectNameL}Id}
	</update>
	
	<!-- 修改 -->
	<update id="editByPd" parameterType="pd">
		update  ${objectModuleEL}${objectNameU}
			set 
				<#list fieldList as var>
				${var[0]} = ${r"#{"}${var[0]}${r"}"}<#if word_has_next>,</#if>
				</#list>
			where 
				${objectNameL}Id = #{${objectNameL}Id}
	</update>
	
	<!-- 删除 -->
	<delete id="deleteById" parameterType="int">
		delete from ${objectModuleEL}${objectNameU} 
		where 
			${objectNameL}Id = #{${objectNameL}Id}
	</delete>
	
	<!-- 删除 -->
	<delete id="deleteByPd" parameterType="pd">
		delete from ${objectModuleEL}${objectNameU} 
		where 
			${objectNameL}Id = #{${objectNameL}Id}
	</delete>
	
	<!-- 通过id获取(类)数据 -->
	<select id="findById" parameterType="int" resultType="${objectModuleEL}${objectNameU}">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
		where 
			${objectNameL}Id = #{${objectNameL}Id}
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="findPdById" parameterType="int" resultType="pd">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
		where 
			${objectNameL}Id = #{${objectNameL}Id}
	</select>
	
	<!-- 通过pd获取(PageData)数据  -->
	<select id="findPdByPd" parameterType="pd" resultType="pd">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
		where 
			${objectNameL}Id = #{${objectNameL}Id}
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="listAll" resultMap="configResultMap">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="listAllPd" parameterType="bgPage" resultType="pd">
		select 
			<include refid="${objectNameL}Columns"/>
		from 
			${objectModuleEL}${objectNameU}
	</select>
	
	<!-- ****************************common * end  ********************************** -->
	
	<!-- ****************************custom * start********************************** -->
	
	
	<!-- ****************************custom * end  ********************************** -->
	
	
</mapper>