package org.ko.prototype.data.master.domain.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此文件由工具生成，请勿修改
 * 生成时间: 2018-02-24 11:26:50
 * 
 */
public final class UserActionLogConstants {

	public static final class Values {
		public static Object serialize(String columnName, String text){
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String USER_ID = "user_id";
		public static final String ACTION_CODE = "action_code";
		public static final String MESSAGE = "message";
		public static final String MILE_STONE = "mile_stone";
		public static final String SESSION_CODE = "session_code";
		public static final String THREAD_CODE = "thread_code";
		public static final String PAGE_CODE = "page_code";
		public static final String BROWSER = "browser";
		public static final String REMOTE_IP = "remote_ip";
		public static final String APP_NAME = "app_name";
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
			, ACTION_CODE.toLowerCase()
			, MESSAGE.toLowerCase()
			, MILE_STONE.toLowerCase()
			, SESSION_CODE.toLowerCase()
			, THREAD_CODE.toLowerCase()
			, PAGE_CODE.toLowerCase()
			, BROWSER.toLowerCase()
			, REMOTE_IP.toLowerCase()
			, APP_NAME.toLowerCase()
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
			fieldMap.put(ACTION_CODE, "actionCode");
			fieldMap.put(MESSAGE, "message");
			fieldMap.put(MILE_STONE, "mileStone");
			fieldMap.put(SESSION_CODE, "sessionCode");
			fieldMap.put(THREAD_CODE, "threadCode");
			fieldMap.put(PAGE_CODE, "pageCode");
			fieldMap.put(BROWSER, "browser");
			fieldMap.put(REMOTE_IP, "remoteIp");
			fieldMap.put(APP_NAME, "appName");
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
	
	private UserActionLogConstants(){}
}
