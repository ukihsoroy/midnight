package org.ko.analysis.rest.department.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ko.analysis.rest.department.condition.QueryDepartmentCondition;
import org.ko.analysis.rest.department.dto.DepartmentDTO;
import org.ko.analysis.store.ads.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends BaseMapper<Department> {

    List<DepartmentDTO> queryList(QueryDepartmentCondition condition);

    int insertList (List<Department> departments);

}
