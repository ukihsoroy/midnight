package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.data.master.dao.mapper.UserActionLogMapper;
import org.ko.prototype.data.master.dao.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.master.domain.bean.UserActionLog;
import org.ko.prototype.data.master.domain.bean.UserActionLogExample;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:24
 * 
 */
@Repository
public class UserActionLogRepository extends GenericRepository<UserActionLog, UserActionLogExample> {


	@Autowired private UserActionLogMapper mapper;

}
