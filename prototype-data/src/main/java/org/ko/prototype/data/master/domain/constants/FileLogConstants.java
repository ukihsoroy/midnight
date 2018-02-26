package org.ko.prototype.data.master.domain.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: 2018-02-24 11:26:41
 * 
 */
public final class FileLogConstants {

	public static final class Values {
		public static final class StorageType {
			public static final short User = 0;			// 用户文件
			public static final short Archive = 1;			// 归档文件
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(User).equals(value)){
					text = "用户文件";
				}else if(Short.valueOf(Archive).equals(value)){
					text = "归档文件";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("用户文件".equals(text)){
					value = User;
				}else if("归档文件".equals(text)){
					value = Archive;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.STORAGE_TYPE.equalsIgnoreCase(columnName)){
				return StorageType.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.STORAGE_TYPE.equalsIgnoreCase(columnName)){
				return StorageType.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String STORAGE_TYPE = "storage_type";
		public static final String ORIG_NAME = "orig_name";
		public static final String LOCAL_NAME = "local_name";
		public static final String LOCAL_PATH = "local_path";
		public static final String HTTP_PATH = "http_path";
		public static final String PARAM = "param";
		public static final String TRACE_CODE_I = "trace_code_i";
		public static final String HOST_NAME = "host_name";
		public static final String CREATE_USER_ID = "create_user_id";
		public static final String CREATE_BY = "create_by";
		public static final String CREATE_DATE = "create_date";

		public static final List<String> COLUMNS = Arrays.asList(new String[]{
			ID.toLowerCase()
			, STORAGE_TYPE.toLowerCase()
			, ORIG_NAME.toLowerCase()
			, LOCAL_NAME.toLowerCase()
			, LOCAL_PATH.toLowerCase()
			, HTTP_PATH.toLowerCase()
			, PARAM.toLowerCase()
			, TRACE_CODE_I.toLowerCase()
			, HOST_NAME.toLowerCase()
			, CREATE_USER_ID.toLowerCase()
			, CREATE_BY.toLowerCase()
			, CREATE_DATE.toLowerCase()
		});
		
		public static final Map<String, String> FIELDS;
		
		static{
			Map<String, String> fieldMap = new HashMap<>();
			fieldMap.put(ID, "id");
			fieldMap.put(STORAGE_TYPE, "storageType");
			fieldMap.put(ORIG_NAME, "origName");
			fieldMap.put(LOCAL_NAME, "localName");
			fieldMap.put(LOCAL_PATH, "localPath");
			fieldMap.put(HTTP_PATH, "httpPath");
			fieldMap.put(PARAM, "param");
			fieldMap.put(TRACE_CODE_I, "traceCodeI");
			fieldMap.put(HOST_NAME, "hostName");
			fieldMap.put(CREATE_USER_ID, "createUserId");
			fieldMap.put(CREATE_BY, "createBy");
			fieldMap.put(CREATE_DATE, "createDate");
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
	
	private FileLogConstants(){}
}
