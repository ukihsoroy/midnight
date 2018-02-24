package org.ko.prototype.data.constants.domain;

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
public final class UserConstants {

	public static final class Values {
		public static final class Gender {
			public static final short Privacy = 0;			// 保密
			public static final short Male = 1;			// 男
			public static final short Female = 2;			// 女
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Privacy).equals(value)){
					text = "保密";
				}else if(Short.valueOf(Male).equals(value)){
					text = "男";
				}else if(Short.valueOf(Female).equals(value)){
					text = "女";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("保密".equals(text)){
					value = Privacy;
				}else if("男".equals(text)){
					value = Male;
				}else if("女".equals(text)){
					value = Female;
				}
				return value;
			}
		}
		public static final class DeleteStatus {
			public static final short Deleted = 0;			// 删除
			public static final short Available = 1;			// 有效
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Deleted).equals(value)){
					text = "删除";
				}else if(Short.valueOf(Available).equals(value)){
					text = "有效";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("删除".equals(text)){
					value = Deleted;
				}else if("有效".equals(text)){
					value = Available;
				}
				return value;
			}
		}
		public static Object serialize(String columnName, String text){
			if(Columns.GENDER.equalsIgnoreCase(columnName)){
				return Gender.convert(text);
			}
			if(Columns.DELETE_STATUS.equalsIgnoreCase(columnName)){
				return DeleteStatus.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.GENDER.equalsIgnoreCase(columnName)){
				return Gender.convert(value);
			}
			if(Columns.DELETE_STATUS.equalsIgnoreCase(columnName)){
				return DeleteStatus.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String LOGIN_NAME = "login_name";
		public static final String PASSWORD = "password";
		public static final String MOBILE = "mobile";
		public static final String EMAIL = "email";
		public static final String NICK_NAME = "nick_name";
		public static final String FULL_NAME = "full_name";
		public static final String GENDER = "gender";
		public static final String AGE = "age";
		public static final String BIRTHDAY = "birthday";
		public static final String AVATAR = "avatar";
		public static final String CREDIT = "credit";
		public static final String BALANCE = "balance";
		public static final String CHECKOUT_COUNT = "checkout_count";
		public static final String LAST_CHECKOUT_TIME = "last_checkout_time";
		public static final String LAST_LOGIN_TIME = "last_login_time";
		public static final String LOGIN_COUNT = "login_count";
		public static final String LAST_LOGIN_IP = "last_login_ip";
		public static final String DELETE_STATUS = "delete_status";
		public static final String LOGIN_TOKEN = "login_token";
		public static final String LOGIN_TOKEN_REFRESH_TIME = "login_token_refresh_time";
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
			, LOGIN_NAME.toLowerCase()
			, PASSWORD.toLowerCase()
			, MOBILE.toLowerCase()
			, EMAIL.toLowerCase()
			, NICK_NAME.toLowerCase()
			, FULL_NAME.toLowerCase()
			, GENDER.toLowerCase()
			, AGE.toLowerCase()
			, BIRTHDAY.toLowerCase()
			, AVATAR.toLowerCase()
			, CREDIT.toLowerCase()
			, BALANCE.toLowerCase()
			, CHECKOUT_COUNT.toLowerCase()
			, LAST_CHECKOUT_TIME.toLowerCase()
			, LAST_LOGIN_TIME.toLowerCase()
			, LOGIN_COUNT.toLowerCase()
			, LAST_LOGIN_IP.toLowerCase()
			, DELETE_STATUS.toLowerCase()
			, LOGIN_TOKEN.toLowerCase()
			, LOGIN_TOKEN_REFRESH_TIME.toLowerCase()
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
			fieldMap.put(LOGIN_NAME, "loginName");
			fieldMap.put(PASSWORD, "password");
			fieldMap.put(MOBILE, "mobile");
			fieldMap.put(EMAIL, "email");
			fieldMap.put(NICK_NAME, "nickName");
			fieldMap.put(FULL_NAME, "fullName");
			fieldMap.put(GENDER, "gender");
			fieldMap.put(AGE, "age");
			fieldMap.put(BIRTHDAY, "birthday");
			fieldMap.put(AVATAR, "avatar");
			fieldMap.put(CREDIT, "credit");
			fieldMap.put(BALANCE, "balance");
			fieldMap.put(CHECKOUT_COUNT, "checkoutCount");
			fieldMap.put(LAST_CHECKOUT_TIME, "lastCheckoutTime");
			fieldMap.put(LAST_LOGIN_TIME, "lastLoginTime");
			fieldMap.put(LOGIN_COUNT, "loginCount");
			fieldMap.put(LAST_LOGIN_IP, "lastLoginIp");
			fieldMap.put(DELETE_STATUS, "deleteStatus");
			fieldMap.put(LOGIN_TOKEN, "loginToken");
			fieldMap.put(LOGIN_TOKEN_REFRESH_TIME, "loginTokenRefreshTime");
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
	
	private UserConstants(){}
}
