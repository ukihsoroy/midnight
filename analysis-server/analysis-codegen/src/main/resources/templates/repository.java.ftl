package ${rootPackage}.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.sigma.data.entity.${entityName};
import org.springframework.stereotype.Repository;
import ${rootPackage}.dto.${entityName}DTO;
import ${rootPackage}.condition.Query${entityName}Condition;


import java.util.List;

@Repository
public interface ${entityName}Repository extends BaseMapper<${entityName}> {

    List<${entityName}DTO> queryList(Query${entityName}Condition condition);

    int insertList (List<${entityName}> ${entityName?uncap_first}s);

}
