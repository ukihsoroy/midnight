package org.ko.prototype.data.master.dao.repository;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableRepository extends AbstractRepository {

	private static final Logger log = LoggerFactory.getLogger(TableRepository.class);
	
	/**
	 * 单条查询
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @param type 结果集转换后的类型
	 * @return 结果集第一个元素
	 * @throws Exception
	 */
	public<V> V findOne(Map<String, Object> params, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildNameSpace("select"), fill(convertEmptyValueToNull(params)));
		unfill(params);
		
		return CollectionUtils.isNotEmpty(objects) ? convertType(objects.get(0), type) : null;
	}

	/**
	 * 按mybatis的sql id（命名sql）查询
	 * @param sqlName sql id名称
	 * @param type 结果集转换后的类型
	 * @return 结果集第一个元素
	 * @throws Exception
	 */
	public<V> V findOne(String sqlName, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildNameSpace(sqlName));
		return CollectionUtils.isNotEmpty(objects) ? convertType(objects.get(0), type) : null;
	}
	
	/**
	 * 按mybatis的sql id（命名sql）查询
	 * @param sqlName mybatis xml中sql id
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @param type 结果集转换后的类型
	 * @return 结果集第一个元素
	 * @throws Exception
	 */
	public<V> V findOne(String sqlName, Map<String, Object> params, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
		return CollectionUtils.isNotEmpty(objects) ? convertType(objects.get(0), type) : null;
	}
	
	/**
	 * 分页查询
	 * @param sqlName mybatis xml中sql id
	 * @param page 分页参数
	 * @param type 结果集转换后的类型
	 * @return 分页查询结果集
	 * @throws Exception
	 */
//	public<V> PageRecord<V> find(String sqlName, Page page, Class<V> type) throws Exception {
//		Page p = normalizePage(page);
//
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put(Page.PAGE_PARAM_NAME, p);
//
//		List<Object> objects = template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
//		List<V> records = convertType(objects, type);
//
//		if(StringUtils.isBlank(page.getCountSqlName())){
//			return new PageRecord<>(records, p);
//		}else{
//			long count = getCustomSqlCount(page);
//			return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//		}
//	}

	/**
	 * 分页查询
	 * @param sqlName mybatis xml中sql id
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @param page 分页参数
	 * @param type 结果集转换后的类型
	 * @return 分页查询结果集
	 * @throws Exception
	 */
//	public<V> PageRecord<V> find(String sqlName, Map<String, Object> params, Page page, Class<V> type) throws Exception {
//		Page p = normalizePage(page);
//
//		params.put(Page.PAGE_PARAM_NAME, p);
//
//		List<Object> objects = template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
//		List<V> records = convertType(objects, type);
//
//		if(StringUtils.isBlank(page.getCountSqlName())){
//			return new PageRecord<>(records, p);
//		}else{
//			long count = getCustomSqlCount(page);
//			return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//		}
//	}

	/**
	 * 分页查询
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @param page 分页参数
	 * @param type 结果集转换后的类型
	 * @return 分页查询结果集
	 * @throws Exception
	 */
//	public<V> PageRecord<V> find(Map<String, Object> params, Page page, Class<V> type) throws Exception {
//		Page p = normalizePage(page);
//
//		params.put(Page.PAGE_PARAM_NAME, p);
//
//		List<Object> objects = template.selectList(buildNameSpace("select"), fill(convertEmptyValueToNull(params)));
//
//		List<V> records = convertType(objects, type);
//		unfill(params);
//
//		if(StringUtils.isBlank(page.getCountSqlName())){
//			return new PageRecord<>(records, p);
//		}else{
//			long count = getCustomSqlCount(page);
//			return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//		}
//	}

	/**
	 * 数据查询
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @param type 结果集转换后的类型
	 * @return 查询结果集
	 * @throws Exception
	 */
	public<V> List<V> find(Map<String, Object> params, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildNameSpace("select"), fill(convertEmptyValueToNull(params)));
		unfill(params);
		
		return convertType(objects, type);
	}
	
	/**
	 * 数据查询
	 * @param sqlName mybatis xml中sql id
	 * @return 查询结果集
	 * @throws Exception
	 */
	public List<Map<String, Object>> find(String sqlName) throws Exception {
		return template.selectList(buildNameSpace(sqlName));
	}

	/**
	 * 数据查询
	 * @param sqlName mybatis xml中sql id
	 * @param type 结果集转换后的类型
	 * @return 查询结果集
	 * @throws Exception
	 */
	public<V> List<V> find(String sqlName, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildNameSpace(sqlName));
		List<V> records = convertType(objects, type);
		return records;
	}

	/**
	 * 数据查询
	 * @param sqlName mybatis xml中sql id
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @param type 结果集转换后的类型
	 * @return 查询结果集
	 * @throws Exception
	 */
	public<V> List<V> find(String sqlName, Map<String, Object> params, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
		List<V> records = convertType(objects, type);
		return records;
	}

	/**
	 * 查询数据条数
	 * @param sqlName mybatis xml中sql id
	 * @return 数据条数
	 */
	public long count(String sqlName){
		return convertToLong(template.selectOne(buildNameSpace(sqlName)));
	}
	
	/**
	 * 查询数据条数
	 * @param sqlName mybatis xml中sql id
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @return
	 */
	public long count(String sqlName, Map<String, Object> params){
		return convertToLong(template.selectOne(buildNameSpace(sqlName), convertEmptyValueToNull(params)));
	}

	/**
	 * 插入默认数据，主键名称默认为id
	 * @return 新增记录主键值
	 * @throws Exception
	 */
	public Object insert() throws Exception {
		return insertOfPrimaryKey("id");
	}
	
	/**
	 * 插入默认数据
	 * @param primaryKeyName 主键列名
	 * @return	新增记录主键值
	 * @throws Exception
	 */
	public Object insertOfPrimaryKey(String primaryKeyName) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put(primaryKeyName, null);
		super.insert(params);
		
		return params.get(primaryKeyName);
	}

	/**
	 * 插入默认数据
	 * @param sqlName mybatis xml中sql id
	 * @return 新增记录主键值（需要xml中配置selectKey）
	 */
	public int insert(String sqlName){
		return template.insert(buildNameSpace(sqlName));
	}

	/**
	 * 插入默认数据，新增记录的主键值会被自动填入到params参数中（需要配置insert标签的key属性）
	 * @param sqlName mybatis xml中sql id
	 * @param params 要插入的数据，key - 字段列名称； value - 值；
	 * @return 插入的条数
	 */
	public int insert(String sqlName, Map<String, Object> params){
		int count = template.insert(buildNameSpace(sqlName), fill(convertEmptyValueToNull(params)));
		unfill(params);
		
		return count;
	}

	/**
	 * 更新数据
	 * @param sqlName mybatis xml中sql id
	 * @return 更新条数
	 */
	public int update(String sqlName){
		return template.update(buildNameSpace(sqlName));
	}
	
	/**
	 * 更新数据
	 * @param sqlName mybatis xml中sql id
	 * @param params 更新条件，key - 字段列名称； value - 值；
	 * @return 更新条数
	 */
	public int update(String sqlName, Map<String, Object> params){
		return template.update(buildNameSpace(sqlName), convertEmptyValueToNull(params));
	}
	
	/**
	 * 删除数据
	 * @param sqlName mybatis xml中sql id
	 * @return 删除条数
	 */
	public int delete(String sqlName){
		return template.delete(buildNameSpace(sqlName));
	}
	
	/**
	 * 删除数据
	 * @param sqlName mybatis xml中sql id
	 * @param params 删除条件，key - 字段列名称； value - 值；
	 * @return
	 */
	public int delete(String sqlName, Map<String, Object> params){
		return template.delete(buildNameSpace(sqlName), convertEmptyValueToNull(params));
	}
	
	@SuppressWarnings("unchecked")
	protected<V> List<V> convertType(List<? extends Object> objects, Class<V> type) throws Exception {
		List<V> records = new ArrayList<>();
		
		if(CollectionUtils.isNotEmpty(objects) && objects.get(0) != null){
			if(type.isAssignableFrom(objects.get(0).getClass())){
				records = (List<V>)objects;
			}else{
				records = new ArrayList<>();
				for(Object o : objects){
					V v = type.newInstance();
					BeanUtils.copyProperties(v, o);
					records.add(v);
				}
			}
		}
		
		return records;
	}
	
	@SuppressWarnings("unchecked")
	protected<V> V convertType(Object object, Class<V> type) throws Exception {
		V record = null;
		
		if(object != null){
			if(type.isAssignableFrom(object.getClass()) || isWrapClass(type)){
				record = (V)object;
			}else{
				record = type.newInstance();
				BeanUtils.copyProperties(record, object);
			}
		}
		
		return record;
	}

	@SuppressWarnings("unchecked")
	protected<V> boolean isWrapClass(Class<V> type) throws Exception {
		boolean isWrap = false;
		Field field = null;
		
		try{
			field = type.getField("TYPE");
		}catch(Exception e){
			// donithing
		}
		
		if(field != null){
			Class<V> cls = (Class<V>)field.get(null);
			if(cls != null){
				isWrap = cls.isPrimitive();
			}
		}
		
		return isWrap;
	}


	
//	protected long getCustomSqlCount(Page page){
//		return MapUtils.isNotEmpty(page.getCountSqlParams()) ? this.count(page.getCountSqlName(), page.getCountSqlParams()) : this.count(page.getCountSqlName());
//	}

	private static long convertToLong(Object obj) {
		long value = 0L;

		try {
			value = obj != null && obj instanceof Long ? (Long)obj : Long.valueOf(String.valueOf(obj));
		} catch (NumberFormatException var4) {
			log.trace(var4.getMessage());
		}

		return value;
	}

}
