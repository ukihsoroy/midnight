package org.ko.prototype.data.dao.repository;

import com.github.pagehelper.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请勿修改此类
 * @author lee
 *
 */
public class GenericRepository<T, E> extends TableRepository {

	private static final String RECORD_PARAM_NAME = "record";
	private static final String EXAMPLE_PARAM_NAME = "example";
	private static final String MAPPER_PACKAGE = "com.joinway.prototype.data.dao.mapper";

	/**
	 * 按主键查询
	 * @param id 主键值
	 * @return 查询结果
	 */
	public T findById(Integer id){
		if(id == null || id == 0){
			return null;
		}
		return template.selectOne(buildMapperNameSpace("selectByPrimaryKey"), id);
	}
	
	/**
	 * 按主键查询
	 * @param id 主键值
	 * @return 查询结果
	 */
	public T findById(Long id){
		if(id == null || id == 0){
			return null;
		}
		return template.selectOne(buildMapperNameSpace("selectByPrimaryKey"), id);
	}

	/**
	 * 按主键查询
	 * @param id 主键值
	 * @param type 结果集转换后的类型
	 * @return 查询结果
	 * @throws Exception
	 */
	public<V> V findById(Integer id, Class<V> type) throws Exception {
		if(id == null || id == 0){
			return null;
		}
		Object object = template.selectOne(buildMapperNameSpace("selectByPrimaryKey"), id);
		return convertType(object, type);
	}
	
	/**
	 * 按主键查询
	 * @param id 主键值
	 * @param type 结果集转换后的类型
	 * @return 查询结果
	 * @throws Exception
	 */
	public<V> V findById(Long id, Class<V> type) throws Exception {
		if(id == null || id == 0){
			return null;
		}
		Object object = template.selectOne(buildMapperNameSpace("selectByPrimaryKey"), id);
		return convertType(object, type);
	}

	/**
	 * 单条查询
	 * @param e 查询条件
	 * @return 查询结果
	 */
	public T findOne(E e){
		List<T> records = template.selectList(buildMapperNameSpace("selectByExample"), e);
		return CollectionUtils.isNotEmpty(records) ? records.get(0) : null;
	}
	
	/**
	 * 单条查询
	 * @param e 查询条件
	 * @param type 结果集转换后的类型
	 * @return 查询结果
	 * @throws Exception
	 */
	public<V> V findOne(E e, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildMapperNameSpace("selectByExample"), e);
		return CollectionUtils.isNotEmpty(objects) ? convertType(objects.get(0), type) : null;
	}
	
	/**
	 * 单条查询
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @return 查询结果
	 */
	public T findOne(Map<String, Object> params){
		List<T> records = template.selectList(buildNameSpace("select"), fill(convertEmptyValueToNull(params)));
		unfill(params);
		
		return CollectionUtils.isNotEmpty(records) ? records.get(0) : null;
	}
	
	/**
	 * 全表查询
	 * @return 查询结果
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception {
		return template.selectList(buildMapperNameSpace("selectByExample"), buildExampleInstance());
	}
	
	/**
	 * 全表查询
	 * @param type 结果集转换后的类型
	 * @return 查询结果
	 * @throws Exception
	 */
	public<V> List<V> findAll(Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildMapperNameSpace("selectByExample"), buildExampleInstance());
		return convertType(objects, type);
	}
	
	/**
	 * 数据查询
	 * @param e 查询条件
	 * @return 查询结果
	 * @throws Exception
	 */
	public List<T> find(E e) throws Exception {
		return template.selectList(buildMapperNameSpace("selectByExample"), normalizeExample(e));
	}
	
	/**
	 * 数据查询
	 * @param e 查询条件
	 * @param type 结果集转换后的类型
	 * @return 查询结果
	 * @throws Exception
	 */
	public<V> List<V> find(E e, Class<V> type) throws Exception {
		List<Object> objects = template.selectList(buildMapperNameSpace("selectByExample"), normalizeExample(e));
		return convertType(objects, type);
	}
	
	/**
	 * 分页查询
	 * @param e 查询条件
	 * @param page 分页参数
	 * @return 查询结果
	 * @throws Exception
	 */
//	public PageRecord<T> find(E e, Page page) throws Exception {
//		Page p = normalizePage(page);
//
//		List<T> records = new ArrayList<>();
//
//		long count = 0;
//		if(StringUtils.isBlank(page.getCountSqlName())){
//			records = template.selectList(buildMapperNameSpace("selectByExampleWithRowbounds"), e, p.getRowBounds());
//			count = page.isQueryTotalCount() ? template.selectOne(buildMapperNameSpace("countByExample"), normalizeExample(e)) : 0;
//		}else{
//			records = template.selectList(buildMapperNameSpace("selectByExampleWithRowbounds"), e);
//			count = getCustomSqlCount(page);
//		}
//
//		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//	}
	
	/**
	 * 分页查询
	 * @param e 查询条件
	 * @param page 分页参数
	 * @param type 结果集转换后的类型
	 * @return 查询结果
	 * @throws Exception
	 */
//	public<V> PageRecord<V> find(E e, Page page, Class<V> type) throws Exception {
//		Page p = normalizePage(page);
//
//		List<Object> objects = new ArrayList<>();
//
//		long count = 0;
//		if(StringUtils.isBlank(page.getCountSqlName())){
//			objects = template.selectList(buildMapperNameSpace("selectByExampleWithRowbounds"), e, p.getRowBounds());
//			count = page.isQueryTotalCount() ? template.selectOne(buildMapperNameSpace("countByExample"), normalizeExample(e)) : 0;
//		}else{
//			objects = template.selectList(buildMapperNameSpace("selectByExampleWithRowbounds"), e);
//			count = getCustomSqlCount(page);
//		}
//		List<V> records = convertType(objects, type);
//
//		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//	}
	
	/**
	 * 分页查询
	 * @param sqlName mybatis xml中sql id
	 * @param page 分页参数
	 * @return 查询结果
	 * @throws Exception
	 */
//	public PageRecord<T> find(String sqlName, Page page) throws Exception {
//		Page p = normalizePage(page);
//
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put(Page.PAGE_PARAM_NAME, p);
//
//		List<T> records = template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
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
	 * @return 查询结果
	 * @throws Exception
	 */
//	public PageRecord<T> find(String sqlName, Map<String, Object> params, Page page) throws Exception {
//		Page p = normalizePage(page);
//
//		params.put(Page.PAGE_PARAM_NAME, p);
//
//		List<T> records = template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
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
	 * @return 查询结果
	 */
//	public PageRecord<T> find(Map<String, Object> params, Page page){
//		Page p = normalizePage(page);
//
//		params.put(Page.PAGE_PARAM_NAME, p);
//
//		List<T> records = template.selectList(buildNameSpace("select"), fill(convertEmptyValueToNull(params)));
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
	 * @return 查询结果
	 */
	public List<T> find(Map<String, Object> params){
		List<T> records = template.selectList(buildNameSpace("select"), fill(convertEmptyValueToNull(params)));
		unfill(params);
		
		return records;
	}
	
	/**
	 * 数据查询
	 * @param sqlName mybatis xml中sql id
	 * @param params 查询条件，key - 字段列名称； value - 值；
	 * @return 查询结果
	 * @throws Exception
	 */
	public List<T> find(String sqlName, Map<String, Object> params) throws Exception {
		return template.selectList(buildNameSpace(sqlName), convertEmptyValueToNull(params));
	}
	
	/**
	 * 插入数据，主键值会自动设置到参数对象中
	 * @param record 要插入的对象
	 * @return 插入条数
	 */
	public int insert(T record){
		if(record == null){
			return 0;
		}
		return template.insert(buildMapperNameSpace("insertSelective"), record);
	}
	
	/**
	 * 更新数据
	 * @param record 要更新的对象
	 * @return 更新条数
	 */
	public int updateById(T record){
		if(record == null){
			return 0;
		}
		return template.update(buildMapperNameSpace("updateByPrimaryKeySelective"), record);
	}
	
	/**
	 * 更新数据
	 * @param record 要更新的对象
	 * @param e 更新条件
	 * @return 更新条数
	 */
	public int update(T record, E e){
		if(record == null || e == null){
			return 0;
		}
		Map<String, Object> param = new HashMap<>();
		param.put(RECORD_PARAM_NAME, record);
		param.put(EXAMPLE_PARAM_NAME, e);
		return template.update(buildMapperNameSpace("updateByExampleSelective"), param);
	}
	
	/**
	 * 删除数据
	 * @param e 删除条件
	 * @return 删除条数
	 */
	public int delete(E e){
		if(e == null){
			return 0;
		}
		return template.delete(buildMapperNameSpace("deleteByExample"), e);
	}
	
	/**
	 * 按主键删除
	 * @param id 主键值
	 * @return 删除条数
	 */
	public int delete(Integer id){
		if(id == null || id == 0){
			return 0;
		}
		return template.delete(buildMapperNameSpace("deleteByPrimaryKey"), id);
	}
	
	/**
	 * 查询条数
	 * @param e 查询条件
	 * @return 数据条数
	 * @throws Exception
	 */
	public long count(E e) throws Exception {
		return template.selectOne(buildMapperNameSpace("countByExample"), normalizeExample(e));
	}
	
	/**
	 * 查询全表条数
	 * @return 数据条数
	 * @throws Exception
	 */
	public long count() throws Exception {
		return template.selectOne(buildMapperNameSpace("countByExample"), normalizeExample(buildExampleInstance()));
	}
	
	protected E normalizeExample(E e) throws Exception {
		return e != null ? e : buildExampleInstance();
	}
	
	@SuppressWarnings("unchecked")
	protected String buildMapperNameSpace(String methodName){
		ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>)(parameterizedType.getActualTypeArguments()[0]);
		return MAPPER_PACKAGE + "." + clazz.getSimpleName() + "Mapper." + methodName;
	}
	
	@SuppressWarnings("unchecked")
	protected T buildDomainInstance() throws Exception {
		ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>)(parameterizedType.getActualTypeArguments()[0]);
		return clazz.newInstance();
	}
	
	@SuppressWarnings("unchecked")
	protected E buildExampleInstance() throws Exception {
		ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
		Class<E> clazz = (Class<E>)(parameterizedType.getActualTypeArguments()[1]);
		return clazz.newInstance();
	}
	
}
