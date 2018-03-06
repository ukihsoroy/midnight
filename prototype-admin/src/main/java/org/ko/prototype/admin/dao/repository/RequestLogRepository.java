package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.RequestLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.master.domain.bean.RequestLog;
import org.ko.prototype.data.master.domain.bean.RequestLogExample;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:19
 * 
 */
@Repository
public class RequestLogRepository extends GenericRepository<RequestLog, RequestLogExample> {


	@Autowired private RequestLogMapper mapper;

}
