package org.ko.prototype.admin.dao.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.SmsLogMapper;
import org.ko.prototype.data.master.domain.bean.SmsLog;
import org.ko.prototype.data.master.domain.bean.SmsLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:21
 * 
 */
@Repository
public class SmsLogRepository extends GenericRepository<SmsLog, SmsLogExample> {


	@Autowired private SmsLogMapper mapper;

	public SmsLog findOneWithBLOBs(SmsLogExample e){
		List<SmsLog> records = mapper.selectByExampleWithBLOBs(e);
		return CollectionUtils.isNotEmpty(records) ? records.get(0) : null;
	}
	
	public<V> V findOneWithBLOBs(SmsLogExample e, Class<V> type) throws Exception {
		List<SmsLog> objects = mapper.selectByExampleWithBLOBs(e);
		return CollectionUtils.isNotEmpty(objects) ? super.convertType(objects.get(0), type) : null;
	}

	public List<SmsLog> findAllWithBLOBs() throws Exception {
		return mapper.selectByExampleWithBLOBs(new SmsLogExample());
	}

	public<V> List<V> findAllWithBLOBs(Class<V> type) throws Exception {
		List<SmsLog> objects = mapper.selectByExampleWithBLOBs(new SmsLogExample());
		return super.convertType(objects, type);
	}
	
	public List<SmsLog> findWithBLOBs(SmsLogExample e) throws Exception {
		return mapper.selectByExampleWithBLOBs(e);
	}

	public<V> List<V> findWithBLOBs(SmsLogExample e, Class<V> type) throws Exception {
		List<SmsLog> objects = mapper.selectByExampleWithBLOBs(e);
		return super.convertType(objects, type);
	}
	
//	public PageRecord<SmsLog> findWithBLOBs(SmsLogExample e, Page page) throws Exception {
//		Page p = normalizePage(page);
//		List<SmsLog> records = mapper.selectByExampleWithBLOBsWithRowbounds(e, p.getRowBounds());
//		long count = page.isQueryTotalCount() ? mapper.countByExample(e) : 0;
//		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//	}
//
//	public<V> PageRecord<V> findWithBLOBs(SmsLogExample e, Page page, Class<V> type) throws Exception {
//		Page p = normalizePage(page);
//		List<SmsLog> objects = mapper.selectByExampleWithBLOBsWithRowbounds(e, p.getRowBounds());
//		long count = page.isQueryTotalCount() ? mapper.countByExample(e) : 0;
//		List<V> records = super.convertType(objects, type);
//		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//	}
}
