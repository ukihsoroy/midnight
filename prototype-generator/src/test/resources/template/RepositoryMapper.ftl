<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.ko.${appName}.${componentName}.dao.repository.${Table}Repository">
	<!--
		此文件初版由工具生成，仅需生成一次，请定制开发
		生成时间: ${now}
	-->
	<resultMap id="${Table}GridResultMap" type="org.ko.${appName}.${componentName}.bean.domain.${Table}Grid" extends="org.ko.${appName}.data.dao.mapper.${Table}Mapper.BaseResultMap">
	</resultMap>

	<sql id="Select">
		select
			<include refid="org.ko.${appName}.data.dao.mapper.${Table}Mapper.Base_Column_List" />
		from ${t} where 1
		<foreach collection="_this" index="key" item="value" open="" close="" separator="">
			<if test="!key.startsWith('_')">
				<if test="value == null">
			and ${r'${key}'} is null
				</if>
				<if test="value != null">
			and ${r'${key}'} = ${r'#{value}'}
				</if>
			</if>
		</foreach>
		<if test="_orderBy != null and _orderBy.trim() != ''">
			order by #${r'{'}_orderBy${r'}'}
		</if>
	</sql>

<#if (textColumnCount == 0)>	
	<select id="select" parameterType="map" resultMap="org.ko.${appName}.data.dao.mapper.${Table}Mapper.BaseResultMap">
<#else>
	<select id="select" parameterType="map" resultMap="org.ko.${appName}.data.dao.mapper.${Table}Mapper.ResultMapWithBLOBs">
</#if>
		<include refid="Select"></include>
	</select>
	
	<select id="count" parameterType="map" resultType="long">
		select count(*) from (
		<include refid="Select"></include>
		) t
	</select>

	<select id="selectGrid" parameterType="map" resultMap="${Table}GridResultMap">
		select 
<#list meta as m>
			<#if m_index!=0>, </#if>${abbr}.${m.columnName} 
</#list>
		from ${t} ${abbr} 
<#if (deleteStatus == "1")>
		where 1 = 1
<#else>
		where ${abbr}.${deleteStatus} = 1
</#if>
<#list meta as m>
	<#if (m.dataType?lower_case == "varchar")>
		<if test="${m.columnName} != null and ${m.columnName}.trim() != ''">
			and ${abbr}.${m.columnName} like concat('%', #${r'{'}${m.columnName}${r'}'}, '%')
		</if>
	<#elseif (m.dataType?lower_case == "datetime") || (m.dataType?lower_case == "date")>
		<if test="${m.columnName}_from != null">
			and date_format(${abbr}.${m.columnName}, '%Y-%m-%d') &gt;= #${r'{'}${m.columnName}_from${r'}'}
		</if>
		<if test="${m.columnName}_to != null">
			and date_format(${abbr}.${m.columnName}, '%Y-%m-%d') &lt;= #${r'{'}${m.columnName}_to${r'}'}
		</if>
		<if test="${m.columnName} != null">
			and date_format(${abbr}.${m.columnName}, '%Y-%m-%d') = #${r'{'}${m.columnName}${r'}'}
		</if>
	<#elseif (m.columnName?lower_case != "delete_status")>
		<if test="${m.columnName} != null">
			and ${abbr}.${m.columnName} = #${r'{'}${m.columnName}${r'}'}
		</if>
	</#if>
</#list>
		order by ${abbr}.${pk} desc
	</select>

	<insert id="insert" parameterType="map" useGeneratedKeys="true" keyProperty="id">
		insert into ${t} 
		<foreach collection="_this" index="key" open="(" close=")" separator=",">
			<if test="!key.startsWith('_')">
			${r'${key}'}
			</if>
		</foreach>
		values
		<foreach collection="_this" index="key" item="value" open="(" close=")" separator=",">
			<if test="!key.startsWith('_')">
			if(length(${r'#{value}'}) = 0, null, ${r'#{value}'})
			</if>
		</foreach>
	</insert>
	
	<update id="update" parameterType="map">
		update ${t} set
		<foreach collection="_this" index="key" item="value" open="" close="" separator=",">
		<if test="key != 'id' and !key.startsWith('_')">
			${r'${key}'} = ${r'#{value}'}
		</if>
		</foreach>
		where id = #${r'{'}id${r'}'}
	</update>
	
	<delete id="delete" parameterType="map">
		delete from ${t} where 1
		<foreach collection="_this" index="key" item="value" open="" close="" separator="">
			<if test="!key.startsWith('_')">
				<if test="value == null">
			and ${r'${key}'} is null
				</if>
				<if test="value != null">
			and ${r'${key}'} = ${r'#{value}'}
				</if>
			</if>
		</foreach>
	</delete>
</mapper>