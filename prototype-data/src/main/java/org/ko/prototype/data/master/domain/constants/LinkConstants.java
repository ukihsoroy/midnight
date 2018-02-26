package org.ko.prototype.data.master.domain.constants;

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
public final class LinkConstants {

	public static final class Values {
		public static final class Status {
			public static final short Invalid = 0;			// 无效
			public static final short Valid = 1;			// 有效
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Invalid).equals(value)){
					text = "无效";
				}else if(Short.valueOf(Valid).equals(value)){
					text = "有效";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("无效".equals(text)){
					value = Invalid;
				}else if("有效".equals(text)){
					value = Valid;
				}
				return value;
			}
		}
		public static final class Type {
			public static final short ResetPassword = 1;			// 重置密码
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(ResetPassword).equals(value)){
					text = "重置密码";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("重置密码".equals(text)){
					value = ResetPassword;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.TYPE.equalsIgnoreCase(columnName)){
				return Type.convert(text);
			}
			if(Columns.STATUS.equalsIgnoreCase(columnName)){
				return Status.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.TYPE.equalsIgnoreCase(columnName)){
				return Type.convert(value);
			}
			if(Columns.STATUS.equalsIgnoreCase(columnName)){
				return Status.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String EMAIL = "email";
		public static final String TYPE = "type";
		public static final String TOKEN = "token";
		public static final String URL = "url";
		public static final String STATUS = "status";
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
			, EMAIL.toLowerCase()
			, TYPE.toLowerCase()
			, TOKEN.toLowerCase()
			, URL.toLowerCase()
			, STATUS.toLowerCase()
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
			fieldMap.put(EMAIL, "email");
			fieldMap.put(TYPE, "type");
			fieldMap.put(TOKEN, "token");
			fieldMap.put(URL, "url");
			fieldMap.put(STATUS, "status");
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
	
	private LinkConstants(){}
}
