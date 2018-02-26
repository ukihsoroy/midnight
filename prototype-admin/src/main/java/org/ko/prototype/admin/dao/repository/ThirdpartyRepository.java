package org.ko.prototype.admin.dao.repository;

import org.ko.prototype.data.master.dao.mapper.ThirdpartyMapper;
import org.ko.prototype.data.master.dao.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.ko.prototype.data.master.domain.bean.Thirdparty;
import org.ko.prototype.data.master.domain.bean.ThirdpartyExample;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: 2018-02-24 13:12:23
 * 
 */
@Repository
public class ThirdpartyRepository extends GenericRepository<Thirdparty, ThirdpartyExample> {


	@Autowired private ThirdpartyMapper mapper;

}
