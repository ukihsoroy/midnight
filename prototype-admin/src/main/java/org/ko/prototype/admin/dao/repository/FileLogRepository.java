package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.data.bean.domain.FileLog;
import org.ko.prototype.data.bean.domain.FileLogExample;
import org.ko.prototype.data.dao.mapper.FileLogMapper;
import org.ko.prototype.data.dao.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:15
 * 
 */
@Repository
public class FileLogRepository extends GenericRepository<FileLog, FileLogExample> {

	@Autowired private FileLogMapper mapper;
}
