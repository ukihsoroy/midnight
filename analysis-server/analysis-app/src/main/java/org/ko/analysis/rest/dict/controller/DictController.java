package org.ko.analysis.rest.dict.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.analysis.conf.api.Response;
import org.ko.analysis.rest.dict.condition.QueryDictCondition;
import org.ko.analysis.rest.dict.dto.DictDTO;
import org.ko.analysis.rest.dict.service.DictService;
import org.ko.analysis.store.ads.domain.Dict;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "字典表接口")
@RestController
@RequestMapping("dict")
@Validated
public class DictController {

    /**
     * 字典表service
     */
    @Autowired
    private DictService dictService;

    @GetMapping
    @ApiOperation("查询全部字典表")
    public Response<List<DictDTO>> queryDictList(@ApiParam("字典表查询参数") @ModelAttribute QueryDictCondition condition) {
        //1. 查询字典表列表数据
        List<Dict> dicts = dictService.queryDictList(condition);

        //2. 如果不为空
        List<DictDTO> dictDTOS = dicts.stream().map(this::map).collect(Collectors.toList());
        return new Response<>(dictDTOS);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID查询字典表")
    public Response<DictDTO> queryDictInfo (@ApiParam("主键") @PathVariable Long id) {
        Dict dict = dictService.queryDictInfo(id);
        return new Response<>(map(dict));
    }

    @PostMapping
    @ApiOperation("新增字典表")
    public Response<Long> createDict (
            @ApiParam("字典表传输对象实体") @RequestBody DictDTO dictDTO) {
        Long dictId = dictService.createDict(map(dictDTO));;
        return new Response<>(dictId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("修改字典表")
    public Response<DictDTO> updateDict (
            @ApiParam("字典表ID主键") @PathVariable Long id,
            @ApiParam("字典表传输对象实体") @RequestBody DictDTO dictDTO) {
        Dict dict = dictService.updateDict(id, map(dictDTO));
        return new Response<>(map(dict));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("删除字典表")
    public Response<Long> deleteDict(@ApiParam("用户ID主键") @PathVariable Long id) {
        Long result = dictService.deleteDict(id);
        return new Response<>(result);
    }

    /**
     * Dict mapTo DictDTO
     * @param dict
     * @return dictDTO
     */
    private DictDTO map (Dict dict) {
        DictDTO dictDTO = new DictDTO();
        BeanUtils.copyProperties(dict, dictDTO);
        return dictDTO;
    }

    /**
     * DictDTO mapTo Dict
     * @param dictDTO
     * @return
     */
    private Dict map (DictDTO dictDTO) {
        Dict dict = Dict.builder().build();
        BeanUtils.copyProperties(dictDTO, dict);
        return dict;
    }

}
