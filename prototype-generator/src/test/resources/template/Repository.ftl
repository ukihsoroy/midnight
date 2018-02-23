package org.ko.${appName}.${componentName}.dao.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

<#if (textColumnCount > 0)>
import org.ko.framework.data.adaptor.bean.Page;
import org.ko.framework.data.adaptor.bean.PageRecord;
</#if>
import org.ko.${appName}.data.bean.domain.${Table};
import org.ko.${appName}.data.bean.domain.${Table}Example;
<#if deleteStatus??>
import org.ko.${appName}.data.constants.domain.${Table}Constants;
</#if>
import org.ko.${appName}.data.dao.mapper.${Table}Mapper;
import org.ko.${appName}.data.dao.repository.GenericRepository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: ${now}
 * 
 */
@Repository
public class ${Table}Repository extends GenericRepository<${Table}, ${Table}Example> {
<#if deleteStatus??>

	private static final Short AVAILABLE_VALUE = ${Table}Constants.Values.DeleteStatus.Available;
	
</#if>
	@Autowired private ${Table}Mapper mapper;

<#if deleteStatus??>
	public List<${Table}> findAllAvailable(){
		${Table}Example e = new ${Table}Example();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public ${Table} findAvailableById(Integer id){
		${Table} record = super.findById(id);
		return Short.valueOf(${Table}Constants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		${Table}Example e = new ${Table}Example();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		${Table} record = new ${Table}();
		record.setId(id);
		record.setDeleteStatus(${Table}Constants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
</#if>
<#if (textColumnCount > 0)>
	public ${Table} findOneWithBLOBs(${Table}Example e){
		List<${Table}> records = mapper.selectByExampleWithBLOBs(e);
		return CollectionUtils.isNotEmpty(records) ? records.get(0) : null;
	}
	
	public<V> V findOneWithBLOBs(${Table}Example e, Class<V> type) throws Exception {
		List<${Table}> objects = mapper.selectByExampleWithBLOBs(e);
		return CollectionUtils.isNotEmpty(objects) ? super.convertType(objects.get(0), type) : null;
	}

	public List<${Table}> findAllWithBLOBs() throws Exception {
		return mapper.selectByExampleWithBLOBs(new ${Table}Example());
	}

	public<V> List<V> findAllWithBLOBs(Class<V> type) throws Exception {
		List<${Table}> objects = mapper.selectByExampleWithBLOBs(new ${Table}Example());
		return super.convertType(objects, type);
	}
	
	public List<${Table}> findWithBLOBs(${Table}Example e) throws Exception {
		return mapper.selectByExampleWithBLOBs(e);
	}

	public<V> List<V> findWithBLOBs(${Table}Example e, Class<V> type) throws Exception {
		List<${Table}> objects = mapper.selectByExampleWithBLOBs(e);
		return super.convertType(objects, type);
	}
	
	public PageRecord<${Table}> findWithBLOBs(${Table}Example e, Page page) throws Exception {
		Page p = normalizePage(page);
		List<${Table}> records = mapper.selectByExampleWithBLOBsWithRowbounds(e, p.getRowBounds());
		long count = page.isQueryTotalCount() ? mapper.countByExample(e) : 0;
		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
	}
	
	public<V> PageRecord<V> findWithBLOBs(${Table}Example e, Page page, Class<V> type) throws Exception {
		Page p = normalizePage(page);
		List<${Table}> objects = mapper.selectByExampleWithBLOBsWithRowbounds(e, p.getRowBounds());
		long count = page.isQueryTotalCount() ? mapper.countByExample(e) : 0;
		List<V> records = super.convertType(objects, type);
		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
	}
</#if>
}
