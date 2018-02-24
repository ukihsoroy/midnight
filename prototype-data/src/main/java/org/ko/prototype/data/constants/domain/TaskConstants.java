package org.ko.prototype.data.constants.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: 2018-02-24 11:26:48
 * 
 */
public final class TaskConstants {

	public static final class Values {
		public static final class ProgressStatus {
			public static final short Success = 1;			// 成功
			public static final short Abort = 2;			// 中止
			public static final short Error = 3;			// 异常
			public static final short Processing = 4;			// 进行中
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Success).equals(value)){
					text = "成功";
				}else if(Short.valueOf(Abort).equals(value)){
					text = "中止";
				}else if(Short.valueOf(Error).equals(value)){
					text = "异常";
				}else if(Short.valueOf(Processing).equals(value)){
					text = "进行中";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("成功".equals(text)){
					value = Success;
				}else if("中止".equals(text)){
					value = Abort;
				}else if("异常".equals(text)){
					value = Error;
				}else if("进行中".equals(text)){
					value = Processing;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.PROGRESS_STATUS.equalsIgnoreCase(columnName)){
				return ProgressStatus.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.PROGRESS_STATUS.equalsIgnoreCase(columnName)){
				return ProgressStatus.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String START_TIME = "start_time";
		public static final String FINISH_TIME = "finish_time";
		public static final String STORAGE_PATH = "storage_path";
		public static final String FILE_URL = "file_url";
		public static final String FILE_NAME = "file_name";
		public static final String PERCENT = "percent";
		public static final String PROGRESS_STATUS = "progress_status";
		public static final String EXCEPTION = "exception";
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
			, NAME.toLowerCase()
			, START_TIME.toLowerCase()
			, FINISH_TIME.toLowerCase()
			, STORAGE_PATH.toLowerCase()
			, FILE_URL.toLowerCase()
			, FILE_NAME.toLowerCase()
			, PERCENT.toLowerCase()
			, PROGRESS_STATUS.toLowerCase()
			, EXCEPTION.toLowerCase()
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
			fieldMap.put(NAME, "name");
			fieldMap.put(START_TIME, "startTime");
			fieldMap.put(FINISH_TIME, "finishTime");
			fieldMap.put(STORAGE_PATH, "storagePath");
			fieldMap.put(FILE_URL, "fileUrl");
			fieldMap.put(FILE_NAME, "fileName");
			fieldMap.put(PERCENT, "percent");
			fieldMap.put(PROGRESS_STATUS, "progressStatus");
			fieldMap.put(EXCEPTION, "exception");
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
	
	private TaskConstants(){}
}
