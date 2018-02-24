package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.bean.domain.Menu;
import org.ko.prototype.data.bean.domain.MenuExample;
import org.ko.prototype.data.constants.domain.MenuConstants;
import org.ko.prototype.data.dao.mapper.MenuMapper;
import org.ko.prototype.data.dao.repository.GenericRepository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:17
 * 
 */
@Repository
public class MenuRepository extends GenericRepository<Menu, MenuExample> {

	private static final Short AVAILABLE_VALUE = MenuConstants.Values.DeleteStatus.Available;
	
	@Autowired private MenuMapper mapper;

	public List<Menu> findAllAvailable(){
		MenuExample e = new MenuExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public Menu findAvailableById(Integer id){
		Menu record = super.findById(id);
		return Short.valueOf(MenuConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		MenuExample e = new MenuExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		Menu record = new Menu();
		record.setId(id);
		record.setDeleteStatus(MenuConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
}
