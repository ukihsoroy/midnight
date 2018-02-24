package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.data.bean.domain.Region;
import org.ko.prototype.data.bean.domain.RegionExample;
import org.ko.prototype.data.constants.domain.RegionConstants;
import org.ko.prototype.data.dao.mapper.RegionMapper;
import org.ko.prototype.data.dao.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:18
 * 
 */
@Repository
public class RegionRepository extends GenericRepository<Region, RegionExample> {

	private static final Short AVAILABLE_VALUE = RegionConstants.Values.DeleteStatus.Available;
	
	@Autowired private RegionMapper mapper;

	public List<Region> findAllAvailable(){
		RegionExample e = new RegionExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public Region findAvailableById(Integer id){
		Region record = super.findById(id);
		return Short.valueOf(RegionConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
//	public int markDelete(Integer id){
//		RegionExample e = new RegionExample();
//		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
//		Region record = new Region();
//		record.setId(id);
//		record.setDeleteStatus(RegionConstants.Values.DeleteStatus.Deleted);
//		return mapper.updateByPrimaryKeySelective(record);
//	}
}
