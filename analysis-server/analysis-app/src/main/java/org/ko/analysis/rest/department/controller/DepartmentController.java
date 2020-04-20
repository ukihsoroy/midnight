package org.ko.analysis.rest.department.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.analysis.conf.api.Response;
import org.ko.analysis.rest.department.condition.QueryDepartmentCondition;
import org.ko.analysis.rest.department.dto.DepartmentDTO;
import org.ko.analysis.rest.department.service.DepartmentService;
import org.ko.analysis.store.ads.domain.Department;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "部门表接口")
@RestController
@RequestMapping("department")
@Validated
public class DepartmentController {

    /**
     * 部门表service
     */
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @ApiOperation("查询全部部门表")
    public Response<List<DepartmentDTO>> queryDepartmentList(@ApiParam("部门表查询参数") @ModelAttribute QueryDepartmentCondition condition) {
        //1. 查询部门表列表数据
        var departments = departmentService.queryDepartmentList(condition);
        List<DepartmentDTO> departmentDTOS = departments.stream().map(this::map).collect(Collectors.toList());
        return new Response<>(departmentDTOS);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID查询部门表")
    public Response<DepartmentDTO> queryDepartmentInfo (@ApiParam("主键") @PathVariable Long id) {
        Department department = departmentService.queryDepartmentInfo(id);
        return new Response<>(map(department));
    }

    @PostMapping
    @ApiOperation("新增部门表")
    public Response<Long> createDepartment (
            @ApiParam("部门表传输对象实体") @RequestBody DepartmentDTO departmentDTO) {
        Long departmentId = departmentService.createDepartment(map(departmentDTO));;
        return new Response<>(departmentId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("修改部门表")
    public Response<DepartmentDTO> updateDepartment (
            @ApiParam("部门表ID主键") @PathVariable Long id,
            @ApiParam("部门表传输对象实体") @RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentService.updateDepartment(id, map(departmentDTO));
        return new Response<>(map(department));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("删除部门表")
    public Response<Long> deleteDepartment(@ApiParam("用户ID主键") @PathVariable Long id) {
        Long result = departmentService.deleteDepartment(id);
        return new Response<>(result);
    }

    /**
     * Department mapTo DepartmentDTO
     * @param department
     * @return departmentDTO
     */
    private DepartmentDTO map (Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department, departmentDTO);
        return departmentDTO;
    }

    /**
     * DepartmentDTO mapTo Department
     * @param departmentDTO
     * @return
     */
    private Department map (DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        return department;
    }

}
