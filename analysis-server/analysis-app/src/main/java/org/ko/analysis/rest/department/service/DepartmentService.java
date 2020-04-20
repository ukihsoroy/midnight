package org.ko.analysis.rest.department.service;


import com.baomidou.mybatisplus.service.IService;
import org.ko.analysis.rest.department.condition.QueryDepartmentCondition;
import org.ko.analysis.store.ads.domain.Department;

import java.util.List;

/**
 * 部门表
 */
public interface DepartmentService extends IService<Department> {

    /**
     * <p>查询部门表列表</p>
     * @param condition
     * @return
     */
    List<Department> queryDepartmentList(QueryDepartmentCondition condition);

    /**
     * <p>通过主键查询部门表详细信息</p>
     * @param id
     * @return
     */
    Department queryDepartmentInfo(Long id);

    /**
     * <p>新增部门表一条数据</p>
     * @param department
     */
    Long createDepartment(Department department);

    /**
     * <p>通过ID更新部门表</p>
     * @param id Department Id
     * @param department
     * @return
     */
    Department updateDepartment(Long id, Department department);

    /**
     * <p>删除部门表数据</p>
     * @param id Department 主键id
     * @return
     */
    Long deleteDepartment(Long id);

}