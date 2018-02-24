package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.bean.domain.Comment;
import org.ko.prototype.data.bean.domain.CommentExample;
import org.ko.prototype.data.constants.domain.CommentConstants;
import org.ko.prototype.data.dao.mapper.CommentMapper;
import org.ko.prototype.data.dao.repository.GenericRepository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:14
 * 
 */
@Repository
public class CommentRepository extends GenericRepository<Comment, CommentExample> {

	private static final Short AVAILABLE_VALUE = CommentConstants.Values.DeleteStatus.Available;
	
	@Autowired private CommentMapper mapper;

	public List<Comment> findAllAvailable(){
		CommentExample e = new CommentExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		return mapper.selectByExample(e);
	}

	public Comment findAvailableById(Integer id){
		Comment record = super.findById(id);
		return Short.valueOf(CommentConstants.Values.DeleteStatus.Available).equals(record.getDeleteStatus()) ? record : null;
	}
	
	public int markDelete(Integer id){
		CommentExample e = new CommentExample();
		e.createCriteria().andDeleteStatusEqualTo(AVAILABLE_VALUE);
		Comment record = new Comment();
		record.setId(id);
		record.setDeleteStatus(CommentConstants.Values.DeleteStatus.Deleted);
		return mapper.updateByPrimaryKeySelective(record);
	}
}
