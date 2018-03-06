package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.master.domain.bean.Comment;
import org.ko.prototype.data.master.domain.bean.CommentExample;
import org.ko.prototype.data.master.domain.constants.CommentConstants;

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
