package org.ko.prototype.data.constants.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: 2018-02-24 11:26:42
 * 
 */
public final class KeywordConstants {

	public static final class Values {
		public static final class WorkType {
			public static final short News = 1;			// 新闻资讯类
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(News).equals(value)){
					text = "新闻资讯类";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("新闻资讯类".equals(text)){
					value = News;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.WORK_TYPE.equalsIgnoreCase(columnName)){
				return WorkType.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.WORK_TYPE.equalsIgnoreCase(columnName)){
				return WorkType.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String WORK_ID = "work_id";
		public static final String WORK_TYPE = "work_type";
		public static final String KEYWORD = "keyword";
		public static final String TRACE_CODE_I = "trace_code_i";
		public static final String TRACE_CODE_U = "trace_code_u";
		public static final String HOST_NAME = "host_name";
		public static final String CREATE_USER_ID = "create_user_id";
		public static final String CREATE_BY = "create_by";
		public static final String CREATE_DATE = "create_date";
		public static final String UPDATE_USER_ID = "update_user_id";
		public static final String UPDATE_BY = "update_by";
		public static final String UPDATE_DATE = "update_date";

		public static final List<String> COLUMNS = Arrays.asList(new String[]{
			ID.toLowerCase()
			, WORK_ID.toLowerCase()
			, WORK_TYPE.toLowerCase()
			, KEYWORD.toLowerCase()
			, TRACE_CODE_I.toLowerCase()
			, TRACE_CODE_U.toLowerCase()
			, HOST_NAME.toLowerCase()
			, CREATE_USER_ID.toLowerCase()
			, CREATE_BY.toLowerCase()
			, CREATE_DATE.toLowerCase()
			, UPDATE_USER_ID.toLowerCase()
			, UPDATE_BY.toLowerCase()
			, UPDATE_DATE.toLowerCase()
		});
		
		public static final Map<String, String> FIELDS;
		
		static{
			Map<String, String> fieldMap = new HashMap<>();
			fieldMap.put(ID, "id");
			fieldMap.put(WORK_ID, "workId");
			fieldMap.put(WORK_TYPE, "workType");
			fieldMap.put(KEYWORD, "keyword");
			fieldMap.put(TRACE_CODE_I, "traceCodeI");
			fieldMap.put(TRACE_CODE_U, "traceCodeU");
			fieldMap.put(HOST_NAME, "hostName");
			fieldMap.put(CREATE_USER_ID, "createUserId");
			fieldMap.put(CREATE_BY, "createBy");
			fieldMap.put(CREATE_DATE, "createDate");
			fieldMap.put(UPDATE_USER_ID, "updateUserId");
			fieldMap.put(UPDATE_BY, "updateBy");
			fieldMap.put(UPDATE_DATE, "updateDate");
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
	
	private KeywordConstants(){}
}
