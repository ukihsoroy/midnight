package ${rootPackage}.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.data.entity.${entityName};
import ${rootPackage}.condition.Query${entityName}Condition;
import ${rootPackage}.dto.${entityName}DTO;
import ${rootPackage}.service.${entityName}Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "${comment}接口")
@RestController
@RequestMapping("${entityName?uncap_first}")
@Validated
public class ${entityName}Controller {

    /**
     * ${comment}service
     */
    @Autowired private ${entityName}Service ${entityName?uncap_first}Service;

    @GetMapping
    @ApiOperation("查询全部${comment}")
    public Response<List<${entityName}DTO>> query${entityName}List(@ApiParam("${comment}查询参数") @ModelAttribute Query${entityName}Condition condition) {
        //1. 查询${comment}列表数据
        List<${entityName}> ${entityName?uncap_first}s = ${entityName?uncap_first}Service.query${entityName}List(condition);

        //2. 如果不为空
        if (CollectionUtils.isNotEmpty(${entityName?uncap_first}s)) {
            List<${entityName}DTO> ${entityName?uncap_first}DTOS = ${entityName?uncap_first}s.stream().map(this::map).collect(Collectors.toList());
            return new Response<>(${entityName?uncap_first}DTOS);
        }
        return new Response<>(SystemCode.EMPTY_DATA);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID查询${comment}")
    public Response<${entityName}DTO> query${entityName}Info (@ApiParam("主键") @PathVariable Long id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.query${entityName}Info(id);
        return new Response<>(map(${entityName?uncap_first}));
    }

    @PostMapping
    @ApiOperation("新增${comment}")
    public Response<Long> create${entityName} (
            @ApiParam("${comment}传输对象实体") @RequestBody ${entityName}DTO ${entityName?uncap_first}DTO) {
        Long ${entityName?uncap_first}Id = ${entityName?uncap_first}Service.create${entityName}(map(${entityName?uncap_first}DTO));;
        return new Response<>(${entityName?uncap_first}Id);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("修改${comment}")
    public Response<${entityName}DTO> update${entityName} (
            @ApiParam("${comment}ID主键") @PathVariable Long id,
            @ApiParam("${comment}传输对象实体") @RequestBody ${entityName}DTO ${entityName?uncap_first}DTO) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.update${entityName}(id, map(${entityName?uncap_first}DTO));
        return new Response<>(map(${entityName?uncap_first}));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("删除${comment}")
    public Response<Long> delete${entityName}(@ApiParam("用户ID主键") @PathVariable Long id) {
        Long result = ${entityName?uncap_first}Service.delete${entityName}(id);
        return new Response<>(result);
    }

    /**
     * ${entityName} mapTo ${entityName}DTO
     * @param ${entityName?uncap_first}
     * @return ${entityName?uncap_first}DTO
     */
    private ${entityName}DTO map (${entityName} ${entityName?uncap_first}) {
        ${entityName}DTO ${entityName?uncap_first}DTO = new ${entityName}DTO();
        BeanUtils.copyProperties(${entityName?uncap_first}, ${entityName?uncap_first}DTO);
        return ${entityName?uncap_first}DTO;
    }

    /**
     * ${entityName}DTO mapTo ${entityName}
     * @param ${entityName?uncap_first}DTO
     * @return
     */
    private ${entityName} map (${entityName}DTO ${entityName?uncap_first}DTO) {
        ${entityName} ${entityName?uncap_first} = new ${entityName}();
        BeanUtils.copyProperties(${entityName?uncap_first}DTO, ${entityName?uncap_first});
        return ${entityName?uncap_first};
    }

}
