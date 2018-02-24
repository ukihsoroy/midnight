package org.ko.prototype.data.constants.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: 2018-02-24 11:26:45
 * 
 */
public final class RequestLogConstants {

	public static final class Values {
		public static final class ExceptionStatus {
			public static final short WithoutException = 0;			// 无异常
			public static final short WithException = 1;			// 有异常
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(WithoutException).equals(value)){
					text = "无异常";
				}else if(Short.valueOf(WithException).equals(value)){
					text = "有异常";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("无异常".equals(text)){
					value = WithoutException;
				}else if("有异常".equals(text)){
					value = WithException;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.EXCEPTION_STATUS.equalsIgnoreCase(columnName)){
				return ExceptionStatus.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.EXCEPTION_STATUS.equalsIgnoreCase(columnName)){
				return ExceptionStatus.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String ACTION_NAME = "action_name";
		public static final String INTERNAL_URL = "internal_url";
		public static final String SITE_URL = "site_url";
		public static final String REQUEST_URL = "request_url";
		public static final String INCOMING_DATE = "incoming_date";
		public static final String OUTGOING_DATE = "outgoing_date";
		public static final String HTTP_METHOD = "http_method";
		public static final String SESSION_CODE = "session_code";
		public static final String THREAD_CODE = "thread_code";
		public static final String PAGE_CODE = "page_code";
		public static final String HOST_NAME = "host_name";
		public static final String CONTENT_TYPE = "content_type";
		public static final String CONTENT_LENGTH = "content_length";
		public static final String HTTP_STATUS = "http_status";
		public static final String EXCEPTION_STATUS = "exception_status";
		public static final String EXCEPTION_MESSAGE = "exception_message";
		public static final String RETURN_CODE = "return_code";
		public static final String RETURN_LENGTH = "return_length";
		public static final String TIME_COST = "time_cost";
		public static final String BROWSER = "browser";
		public static final String PROTOCOL = "protocol";
		public static final String APP_NAME = "app_name";
		public static final String APP_VERSION = "app_version";
		public static final String NODE_NAME = "node_name";
		public static final String REMOTE_IP = "remote_ip";
		public static final String CHANNEL = "channel";
		public static final String TRACE_CODE = "trace_code";
		public static final String CREATE_USER_ID = "create_user_id";
		public static final String CREATE_BY = "create_by";
		public static final String CREATE_DATE = "create_date";
		public static final String UPDATE_USER_ID = "update_user_id";
		public static final String UPDATE_BY = "update_by";
		public static final String UPDATE_DATE = "update_date";

		public static final List<String> COLUMNS = Arrays.asList(new String[]{
			ID.toLowerCase()
			, USER_ID.toLowerCase()
			, ACTION_NAME.toLowerCase()
			, INTERNAL_URL.toLowerCase()
			, SITE_URL.toLowerCase()
			, REQUEST_URL.toLowerCase()
			, INCOMING_DATE.toLowerCase()
			, OUTGOING_DATE.toLowerCase()
			, HTTP_METHOD.toLowerCase()
			, SESSION_CODE.toLowerCase()
			, THREAD_CODE.toLowerCase()
			, PAGE_CODE.toLowerCase()
			, HOST_NAME.toLowerCase()
			, CONTENT_TYPE.toLowerCase()
			, CONTENT_LENGTH.toLowerCase()
			, HTTP_STATUS.toLowerCase()
			, EXCEPTION_STATUS.toLowerCase()
			, EXCEPTION_MESSAGE.toLowerCase()
			, RETURN_CODE.toLowerCase()
			, RETURN_LENGTH.toLowerCase()
			, TIME_COST.toLowerCase()
			, BROWSER.toLowerCase()
			, PROTOCOL.toLowerCase()
			, APP_NAME.toLowerCase()
			, APP_VERSION.toLowerCase()
			, NODE_NAME.toLowerCase()
			, REMOTE_IP.toLowerCase()
			, CHANNEL.toLowerCase()
			, TRACE_CODE.toLowerCase()
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
			fieldMap.put(ACTION_NAME, "actionName");
			fieldMap.put(INTERNAL_URL, "internalUrl");
			fieldMap.put(SITE_URL, "siteUrl");
			fieldMap.put(REQUEST_URL, "requestUrl");
			fieldMap.put(INCOMING_DATE, "incomingDate");
			fieldMap.put(OUTGOING_DATE, "outgoingDate");
			fieldMap.put(HTTP_METHOD, "httpMethod");
			fieldMap.put(SESSION_CODE, "sessionCode");
			fieldMap.put(THREAD_CODE, "threadCode");
			fieldMap.put(PAGE_CODE, "pageCode");
			fieldMap.put(HOST_NAME, "hostName");
			fieldMap.put(CONTENT_TYPE, "contentType");
			fieldMap.put(CONTENT_LENGTH, "contentLength");
			fieldMap.put(HTTP_STATUS, "httpStatus");
			fieldMap.put(EXCEPTION_STATUS, "exceptionStatus");
			fieldMap.put(EXCEPTION_MESSAGE, "exceptionMessage");
			fieldMap.put(RETURN_CODE, "returnCode");
			fieldMap.put(RETURN_LENGTH, "returnLength");
			fieldMap.put(TIME_COST, "timeCost");
			fieldMap.put(BROWSER, "browser");
			fieldMap.put(PROTOCOL, "protocol");
			fieldMap.put(APP_NAME, "appName");
			fieldMap.put(APP_VERSION, "appVersion");
			fieldMap.put(NODE_NAME, "nodeName");
			fieldMap.put(REMOTE_IP, "remoteIp");
			fieldMap.put(CHANNEL, "channel");
			fieldMap.put(TRACE_CODE, "traceCode");
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
	
	private RequestLogConstants(){}
}
