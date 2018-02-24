package org.ko.prototype.admin.dao.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.bean.domain.Task;
import org.ko.prototype.data.bean.domain.TaskExample;
import org.ko.prototype.data.constants.domain.TaskConstants;
import org.ko.prototype.data.dao.mapper.TaskMapper;
import org.ko.prototype.data.dao.repository.GenericRepository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:22
 * 
 */
@Repository
public class TaskRepository extends GenericRepository<Task, TaskExample> {

	@Autowired private TaskMapper mapper;

}
