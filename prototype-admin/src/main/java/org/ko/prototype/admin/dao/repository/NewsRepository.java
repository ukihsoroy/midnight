package org.ko.prototype.admin.dao.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.NewsMapper;
import org.ko.prototype.data.master.domain.bean.News;
import org.ko.prototype.data.master.domain.bean.NewsExample;
import org.ko.prototype.data.master.domain.constants.NewsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:18
 * 
 */
@Repository
public class NewsRepository extends GenericRepository<News, NewsExample> {

	private static final Short AVAILABLE_VALUE = NewsConstants.Values.DeleteStatus.Available;
	
	@Autowired private NewsMapper mapper;

	public List<News> findAllAvailable(){
		NewsExample e = new NewsExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public News findAvailableById(Integer id){
		News record = super.findById(id);
		return Short.valueOf(NewsConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		NewsExample e = new NewsExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		News record = new News();
		record.setId(id);
		record.setDeleteStatus(NewsConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
	public News findOneWithBLOBs(NewsExample e){
		List<News> records = mapper.selectByExampleWithBLOBs(e);
		return CollectionUtils.isNotEmpty(records) ? records.get(0) : null;
	}
	
	public<V> V findOneWithBLOBs(NewsExample e, Class<V> type) throws Exception {
		List<News> objects = mapper.selectByExampleWithBLOBs(e);
		return CollectionUtils.isNotEmpty(objects) ? super.convertType(objects.get(0), type) : null;
	}

	public List<News> findAllWithBLOBs() throws Exception {
		return mapper.selectByExampleWithBLOBs(new NewsExample());
	}

	public<V> List<V> findAllWithBLOBs(Class<V> type) throws Exception {
		List<News> objects = mapper.selectByExampleWithBLOBs(new NewsExample());
		return super.convertType(objects, type);
	}
	
	public List<News> findWithBLOBs(NewsExample e) throws Exception {
		return mapper.selectByExampleWithBLOBs(e);
	}

	public<V> List<V> findWithBLOBs(NewsExample e, Class<V> type) throws Exception {
		List<News> objects = mapper.selectByExampleWithBLOBs(e);
		return super.convertType(objects, type);
	}
	
//	public PageRecord<News> findWithBLOBs(NewsExample e, Page page) throws Exception {
//		Page p = normalizePage(page);
//		List<News> records = mapper.selectByExampleWithBLOBsWithRowbounds(e, p.getRowBounds());
//		long count = page.isQueryTotalCount() ? mapper.countByExample(e) : 0;
//		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//	}
//
//	public<V> PageRecord<V> findWithBLOBs(NewsExample e, Page page, Class<V> type) throws Exception {
//		Page p = normalizePage(page);
//		List<News> objects = mapper.selectByExampleWithBLOBsWithRowbounds(e, p.getRowBounds());
//		long count = page.isQueryTotalCount() ? mapper.countByExample(e) : 0;
//		List<V> records = super.convertType(objects, type);
//		return new PageRecord<>(records, p.getCurrentPage(), p.getRows(), count);
//	}
}
