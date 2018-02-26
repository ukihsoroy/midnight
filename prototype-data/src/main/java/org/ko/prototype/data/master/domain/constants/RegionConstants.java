package org.ko.prototype.data.master.domain.constants;

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
public final class RegionConstants {

	public static final class Values {
		public static final class Rank {
			public static final short Nation = 1;			// 国家
			public static final short Province = 2;			// 省
			public static final short City = 3;			// 市
			public static final short Country = 4;			// 区
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Nation).equals(value)){
					text = "国家";
				}else if(Short.valueOf(Province).equals(value)){
					text = "省";
				}else if(Short.valueOf(City).equals(value)){
					text = "市";
				}else if(Short.valueOf(Country).equals(value)){
					text = "区";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("国家".equals(text)){
					value = Nation;
				}else if("省".equals(text)){
					value = Province;
				}else if("市".equals(text)){
					value = City;
				}else if("区".equals(text)){
					value = Country;
				}
				return value;
			}
		}
		public static final class GovType {
			public static final short Capital = 1;			// 首都
			public static final short Other = 0;			// 其他
			public static String convert(Short value){
				String text = String.valueOf(value);
				if(Short.valueOf(Capital).equals(value)){
					text = "首都";
				}else if(Short.valueOf(Other).equals(value)){
					text = "其他";
				}
				return text;
			}
			public static Short convert(String text){
				Short value = null;
				if("首都".equals(text)){
					value = Capital;
				}else if("其他".equals(text)){
					value = Other;
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
			if(Columns.RANK.equalsIgnoreCase(columnName)){
				return Rank.convert(text);
			}
			if(Columns.GOV_TYPE.equalsIgnoreCase(columnName)){
				return GovType.convert(text);
			}
			if(Columns.DELETE_STATUS.equalsIgnoreCase(columnName)){
				return DeleteStatus.convert(text);
			}
			return text;
		}
		public static Object deserialize(String columnName, Short value){
			if(Columns.RANK.equalsIgnoreCase(columnName)){
				return Rank.convert(value);
			}
			if(Columns.GOV_TYPE.equalsIgnoreCase(columnName)){
				return GovType.convert(value);
			}
			if(Columns.DELETE_STATUS.equalsIgnoreCase(columnName)){
				return DeleteStatus.convert(value);
			}
			return value;
		}
	}
		
	public static final class Columns {
		public static final String ID = "id";
		public static final String CITY_ID = "city_id";
		public static final String ROOT_ID = "root_id";
		public static final String PARENT_ID = "parent_id";
		public static final String CITY_PATH = "city_path";
		public static final String RANK = "rank";
		public static final String CITY = "city";
		public static final String CITY_EN = "city_en";
		public static final String CITY_TYPE = "city_type";
		public static final String GOV_TYPE = "gov_type";
		public static final String POST = "post";
		public static final String AREA_CODE = "area_code";
		public static final String SHORTEN = "shorten";
		public static final String KEYWORD = "keyword";
		public static final String SEQ = "seq";
		public static final String DELETE_STATUS = "delete_status";
		public static final String CREATE_USER_ID = "create_user_id";
		public static final String CREATE_BY = "create_by";
		public static final String CREATE_DATE = "create_date";
		public static final String UPDATE_USER_ID = "update_user_id";
		public static final String UPDATE_BY = "update_by";
		public static final String UPDATE_DATE = "update_date";

		public static final List<String> COLUMNS = Arrays.asList(new String[]{
			ID.toLowerCase()
			, CITY_ID.toLowerCase()
			, ROOT_ID.toLowerCase()
			, PARENT_ID.toLowerCase()
			, CITY_PATH.toLowerCase()
			, RANK.toLowerCase()
			, CITY.toLowerCase()
			, CITY_EN.toLowerCase()
			, CITY_TYPE.toLowerCase()
			, GOV_TYPE.toLowerCase()
			, POST.toLowerCase()
			, AREA_CODE.toLowerCase()
			, SHORTEN.toLowerCase()
			, KEYWORD.toLowerCase()
			, SEQ.toLowerCase()
			, DELETE_STATUS.toLowerCase()
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
			fieldMap.put(CITY_ID, "cityId");
			fieldMap.put(ROOT_ID, "rootId");
			fieldMap.put(PARENT_ID, "parentId");
			fieldMap.put(CITY_PATH, "cityPath");
			fieldMap.put(RANK, "rank");
			fieldMap.put(CITY, "city");
			fieldMap.put(CITY_EN, "cityEn");
			fieldMap.put(CITY_TYPE, "cityType");
			fieldMap.put(GOV_TYPE, "govType");
			fieldMap.put(POST, "post");
			fieldMap.put(AREA_CODE, "areaCode");
			fieldMap.put(SHORTEN, "shorten");
			fieldMap.put(KEYWORD, "keyword");
			fieldMap.put(SEQ, "seq");
			fieldMap.put(DELETE_STATUS, "deleteStatus");
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
	
	private RegionConstants(){}
}
