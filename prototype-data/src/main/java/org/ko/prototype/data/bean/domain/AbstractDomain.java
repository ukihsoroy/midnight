package org.ko.prototype.data.bean.domain;

import org.apache.commons.beanutils.PropertyUtils;
import org.mybatis.spring.SqlSessionTemplate;

public abstract class AbstractDomain extends BaseBean {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
//
//	private static final String MAPPER_PACKAGE = "org.ko.prototype.data.dao.mapper";
//
//	public int insert(){
//		return getTemplate().insert(buildMapperNameSpace("insertSelective"), this);
//	}
//
//	public int update(){
//		return getTemplate().update(buildMapperNameSpace("updateByPrimaryKeySelective"), this);
//	}
//
//	public int delete() throws Exception {
//		return getTemplate().delete(buildMapperNameSpace("deleteByPrimaryKey"), getPrimaryKey());
//	}
//
//	private SqlSessionTemplate getTemplate(){
//		return FrameworkHelper.getBean(SqlSessionTemplate.class);
//	}
//
//	private String buildMapperNameSpace(String methodName){
//		return MAPPER_PACKAGE + "." + this.getClass().getSimpleName() + "Mapper." + methodName;
//	}
//
//	private Object getPrimaryKey() throws Exception {
//		return PropertyUtils.getProperty(this, "id");
//	}
}
