package org.ko.prototype.data.master.domain.constants;

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
public final class SmsLogConstants {

	public static final class Values {
		public static final class FlowType {
			public static final short Register = 1;			// 注册
			public static final short Login = 2;			// 登录
			public static final short Password = 3;			// 重置密码
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Register).equals(value)){
					text = "注册";
				}else if(Short.valueOf(Login).equals(value)){
					text = "登录";
				}else if(Short.valueOf(Password).equals(value)){
					text = "重置密码";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("注册".equals(text)){
					value = Register;
				}else if("登录".equals(text)){
					value = Login;
				}else if("重置密码".equals(text)){
					value = Password;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.FLOW_TYPE.equalsIgnoreCase(columnName)){
				return FlowType.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.FLOW_TYPE.equalsIgnoreCase(columnName)){
				return FlowType.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String MOBILE = "mobile";
		public static final String FLOW_TYPE = "flow_type";
		public static final String START_TIME = "start_time";
		public static final String END_TIME = "end_time";
		public static final String COST = "cost";
		public static final String STATUS_CODE = "status_code";
		public static final String INPUT = "input";
		public static final String OUTPUT = "output";
		public static final String RETRY_COUNT = "retry_count";
		public static final String ERROR_MESSAGE = "error_message";
		public static final String TRACE_CODE = "trace_code";
		public static final String HOST_NAME = "host_name";
		public static final String CREATE_USER_ID = "create_user_id";
		public static final String CREATE_BY = "create_by";
		public static final String CREATE_DATE = "create_date";
		public static final String UPDATE_USER_ID = "update_user_id";
		public static final String UPDATE_BY = "update_by";
		public static final String UPDATE_DATE = "update_date";

		public static final List<String> COLUMNS = Arrays.asList(new String[]{
			ID.toLowerCase()
			, MOBILE.toLowerCase()
			, FLOW_TYPE.toLowerCase()
			, START_TIME.toLowerCase()
			, END_TIME.toLowerCase()
			, COST.toLowerCase()
			, STATUS_CODE.toLowerCase()
			, INPUT.toLowerCase()
			, OUTPUT.toLowerCase()
			, RETRY_COUNT.toLowerCase()
			, ERROR_MESSAGE.toLowerCase()
			, TRACE_CODE.toLowerCase()
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
			fieldMap.put(MOBILE, "mobile");
			fieldMap.put(FLOW_TYPE, "flowType");
			fieldMap.put(START_TIME, "startTime");
			fieldMap.put(END_TIME, "endTime");
			fieldMap.put(COST, "cost");
			fieldMap.put(STATUS_CODE, "statusCode");
			fieldMap.put(INPUT, "input");
			fieldMap.put(OUTPUT, "output");
			fieldMap.put(RETRY_COUNT, "retryCount");
			fieldMap.put(ERROR_MESSAGE, "errorMessage");
			fieldMap.put(TRACE_CODE, "traceCode");
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
	
	private SmsLogConstants(){}
}
