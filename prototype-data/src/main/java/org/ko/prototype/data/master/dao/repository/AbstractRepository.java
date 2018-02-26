package org.ko.prototype.data.master.dao.repository;

import com.github.pagehelper.Page;
import org.apache.commons.collections.MapUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 请勿修改此类
 * @author lee
 *
 */
public abstract class AbstractRepository {

	private static final Logger log = LoggerFactory.getLogger(AbstractRepository.class);

	private static final String PARAM_SELF = "_this";
	
	private static final int DEFAULT_PAGE_ROWS = 10;
	
	@Autowired
    protected SqlSessionTemplate template;

	/**
	 * 插入数据
	 * @param params 要插入的数据，key - 字段列名称； value - 值；
	 * @return 插入条数
	 */
	public int insert(Map<String, Object> params){
		if(MapUtils.isEmpty(params)){
			return 0;
		}
		int count = template.insert(buildNameSpace("insert"), fill(convertEmptyValueToNull(params)));
		unfill(params);
		return count;
	}

	/**
	 * 插入数据
	 * @param sqlName mybatis xml中sql id（命名sql）
	 * @param params 要插入的数据，key - 字段列名称； value - 值；
	 * @return 插入条数
	 */
	public int insert(String sqlName, Map<String, Object> params){
		if(MapUtils.isEmpty(params)){
			return 0;
		}
		int count = template.insert(buildNameSpace(sqlName), fill(convertEmptyValueToNull(params)));
		unfill(params);
		return count;
	}

	/**
	 * 按主键更新，默认主键名称为id
	 * @param params 要更新的数据，必须包含id键值，key - 字段列名称； value - 值；
	 * @return 更新条数
	 */
	public int updateById(Map<String, Object> params){
		if(MapUtils.isEmpty(params)){
			return 0;
		}
		int count = template.update(buildNameSpace("update"), fill(convertEmptyValueToNull(params)));
		unfill(params);
		return count;
	}
	
	/**
	 * 删除数据
	 * @param params 要删除的数据，key - 字段列名称； value - 值；
	 * @return
	 */
	public int delete(Map<String, Object> params){
		if(MapUtils.isEmpty(params)){
			return 0;
		}
		int count = template.delete(buildNameSpace("delete"), fill(params));
		unfill(params);
		return count;
	}
	
	/**
	 * 查询条数
	 * @param params 查询参数，key - 字段列名称； value - 值；
	 * @return 数据条数
	 */
	public long count(Map<String, Object> params){
		long count = template.selectOne(buildNameSpace("count"), fill(params));
		unfill(params);
		return count;
	}
	
	protected String getName(){
		return this.getClass().getSimpleName();
	}
	
	protected String buildNameSpace(String methodName){
		return this.getClass().getCanonicalName() + "." + methodName;
	}
	
	protected Page normalizePage(Page page){
		return page == null ? new Page(1, DEFAULT_PAGE_ROWS) : page;
	}
	
	protected Map<String, Object> fill(Map<String, Object> params){
		params.put(PARAM_SELF, params);
		return params;
	}
	
	protected void unfill(Map<String, Object> params){
		params.remove(PARAM_SELF);
	}
	
	/**
	 * 将参数中的空字符串值转为null
	 * @param params
	 * @return
	 */
	protected Map<String, Object> convertEmptyValueToNull(Map<String, Object> params){
		if(MapUtils.isEmpty(params)){
			return params;
		}
		
		Set<Entry<String, Object>> entries = params.entrySet();
		for(Entry<String, Object> entry : entries){
			Object value = entry.getValue();
			if(value != null && (value instanceof String || value instanceof Character || value instanceof StringBuilder || value instanceof StringBuffer) && value.toString().trim().length() == 0){
				params.put(entry.getKey(), null);
			}
		}
		return params;
	}
}
