package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.core.repository.GenericRepository;
import org.ko.prototype.data.master.dao.mapper.MailDateMapper;
import org.ko.prototype.data.master.domain.bean.MailDate;
import org.ko.prototype.data.master.domain.bean.MailDateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:17
 * 
 */
@Repository
public class MailDateRepository extends GenericRepository<MailDate, MailDateExample> {

	@Autowired private MailDateMapper mapper;
}
