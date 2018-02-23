package org.ko.${appName}.data.constants.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: ${now}
 * 
 */
public final class ${Table}Constants {

	public static final class Values {
<#list values?keys as key>
		public static final class ${key} {
	<#list values[key] as line>
			public static final ${line.type} ${line.name} = <#if line.type == "String">"</#if>${line.value}<#if line.type == "String">"</#if>;			// ${line.comment}
	</#list>
			public static String convert(Short value){
				String text = String.valueOf(value);
	<#list values[key] as line>
		<#if line_index == 0>
				if(Short.valueOf(${line.name}).equals(value)){
		<#else>
				}else if(Short.valueOf(${line.name}).equals(value)){
		</#if>
					text = "${line.comment}";
	</#list>
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
	<#list values[key] as line>
		<#if line_index == 0>
				if("${line.comment}".equals(text)){
		<#else>
				}else if("${line.comment}".equals(text)){
		</#if>
					value = ${line.name};
	</#list>
				}
				return value;
			}
		}
</#list>
		public static Object serialize(String columnName, String text){
<#list meta as m>
	<#if m.comment?contains("#")>
			if(Columns.${m.columnName?upper_case}.equalsIgnoreCase(columnName)){
				return ${m.fieldName?cap_first}.convert(text);
			}
	</#if>
</#list>
			return text;
		}
		public static Object deserialize(String columnName, Short value){
<#list meta as m>
	<#if m.comment?contains("#")>
			if(Columns.${m.columnName?upper_case}.equalsIgnoreCase(columnName)){
				return ${m.fieldName?cap_first}.convert(value);
			}
	</#if>
</#list>
			return value;
		}
	}
		
	public static final class Columns {
<#list columns as column>
		public static final String ${column?upper_case} = "${column}";
</#list>

		public static final List<String> COLUMNS = Arrays.asList(new String[]{
<#list columns as column>
			<#if column_index!=0>, </#if>${column?upper_case}.toLowerCase()
</#list>
		});
		
		public static final Map<String, String> FIELDS;
		
		static{
			Map<String, String> fieldMap = new HashMap<>();
<#list meta as m>
			fieldMap.put(${m.columnName?upper_case}, "${m.fieldName}");
</#list>
			FIELDS = Collections.unmodifiableMap(fieldMap);
		}
		
		public static final List<String> AUDIT_COLUMNS = Arrays.asList(new String[]{
			"delete_status"
			, "tx_code"
			, "create_user_id"
			, "create_by"
			, "create_date"
			, "update_user_id"
			, "update_by"
			, "update_date"
		});
		
		public static boolean contains(String columnName){
			return columnName != null ? COLUMNS.contains(columnName.toLowerCase()) : false;
		}
	}
	
	private ${Table}Constants(){}
}
