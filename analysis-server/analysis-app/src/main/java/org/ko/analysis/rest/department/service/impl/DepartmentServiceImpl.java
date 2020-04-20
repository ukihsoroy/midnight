package org.ko.analysis.rest.department.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.ko.analysis.conf.api.ResponseCode;
import org.ko.analysis.conf.exp.BusinessException;
import org.ko.analysis.rest.department.condition.QueryDepartmentCondition;
import org.ko.analysis.rest.department.repository.DepartmentRepository;
import org.ko.analysis.rest.department.service.DepartmentService;
import org.ko.analysis.store.ads.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DepartmentServiceImpl extends ServiceImpl<DepartmentRepository, Department> implements DepartmentService {

    /**
     * 部门表数据库对象
     */
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> queryDepartmentList(QueryDepartmentCondition condition) {
        return departmentRepository.selectList(null);
    }

    @Override
    public Department queryDepartmentInfo(Long id) {
        return departmentRepository.selectById(id);
    }

    @Override
    public Long createDepartment(Department department) {
        if (departmentRepository.insert(department) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return department.getId();
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        department.setId(id);
        if (departmentRepository.updateById(department) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return department;
    }

    @Override
    public Long deleteDepartment(Long id) {
        if (departmentRepository.deleteById(id) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return id;
    }


}