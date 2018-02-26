package org.ko.prototype.data.master.domain.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: 2018-02-24 11:26:49
 * 
 */
public final class ThirdpartyConstants {

	public static final class Values {
		public static final class Type {
			public static final short WeiXin = 1;			// 微信
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(WeiXin).equals(value)){
					text = "微信";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("微信".equals(text)){
					value = WeiXin;
				}
				return value;
			}
		}
		public static final class EnableStatus {
			public static final short Disabled = 0;			// 禁用
			public static final short Enabled = 1;			// 启用
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Disabled).equals(value)){
					text = "禁用";
				}else if(Short.valueOf(Enabled).equals(value)){
					text = "启用";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("禁用".equals(text)){
					value = Disabled;
				}else if("启用".equals(text)){
					value = Enabled;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.TYPE.equalsIgnoreCase(columnName)){
				return Type.convert(text);
			}
			if(Columns.ENABLE_STATUS.equalsIgnoreCase(columnName)){
				return EnableStatus.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.TYPE.equalsIgnoreCase(columnName)){
				return Type.convert(value);
			}
			if(Columns.ENABLE_STATUS.equalsIgnoreCase(columnName)){
				return EnableStatus.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String THIRD_NAME = "third_name";
		public static final String PASSWORD = "password";
		public static final String TYPE = "type";
		public static final String ENABLE_STATUS = "enable_status";
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
			, USER_ID.toLowerCase()
			, THIRD_NAME.toLowerCase()
			, PASSWORD.toLowerCase()
			, TYPE.toLowerCase()
			, ENABLE_STATUS.toLowerCase()
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
			fieldMap.put(USER_ID, "userId");
			fieldMap.put(THIRD_NAME, "thirdName");
			fieldMap.put(PASSWORD, "password");
			fieldMap.put(TYPE, "type");
			fieldMap.put(ENABLE_STATUS, "enableStatus");
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
	
	private ThirdpartyConstants(){}
}
