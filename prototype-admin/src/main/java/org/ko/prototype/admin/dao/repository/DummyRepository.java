package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.DummyMapper;
import org.ko.prototype.data.master.domain.bean.Dummy;
import org.ko.prototype.data.master.domain.bean.DummyExample;
import org.ko.prototype.data.master.domain.constants.DummyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:14
 * 
 */
@Repository
public class DummyRepository extends GenericRepository<Dummy, DummyExample> {

	private static final Short AVAILABLE_VALUE = DummyConstants.Values.DeleteStatus.Available;
	
	@Autowired private DummyMapper mapper;

	public List<Dummy> findAllAvailable(){
		DummyExample e = new DummyExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public Dummy findAvailableById(Integer id){
		Dummy record = super.findById(id);
		return Short.valueOf(DummyConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		DummyExample e = new DummyExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		Dummy record = new Dummy();
		record.setId(id);
		record.setDeleteStatus(DummyConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
}
