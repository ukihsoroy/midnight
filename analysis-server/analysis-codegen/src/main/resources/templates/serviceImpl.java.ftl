package ${rootPackage}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.data.entity.${entityName};
import ${rootPackage}.condition.Query${entityName}Condition;
import ${rootPackage}.repository.${entityName}Repository;
import ${rootPackage}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ${entityName}ServiceImpl extends ServiceImpl<${entityName}Repository, ${entityName}> implements ${entityName}Service {

    /**
     * ${comment}数据库对象
     */
    @Autowired private ${entityName}Repository ${entityName?uncap_first}Repository;

    @Override
    public List<${entityName}> query${entityName}List(Query${entityName}Condition condition) {
        return ${entityName?uncap_first}Repository.selectList(new QueryWrapper<>());
    }

    @Override
    public ${entityName} query${entityName}Info(Long id) {
        return ${entityName?uncap_first}Repository.selectById(id);
    }

    @Override
    public Long create${entityName}(${entityName} ${entityName?uncap_first}) {
        if (${entityName?uncap_first}Repository.insert(${entityName?uncap_first}) == 0) {
            throw new TransactionalException(SystemCode.INSERT_ERROR);
        }
        return ${entityName?uncap_first}.getId();
    }

    @Override
    public ${entityName} update${entityName}(Long id, ${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}.setId(id);
        if (${entityName?uncap_first}Repository.updateById(${entityName?uncap_first}) == 0) {
            throw new TransactionalException(SystemCode.UPDATE_ERROR);
        }
        return ${entityName?uncap_first};
    }

    @Override
    public Long delete${entityName}(Long id) {
        if (${entityName?uncap_first}Repository.deleteById(id) == 0) {
            throw new TransactionalException(SystemCode.DELETE_ERROR);
        }
        return id;
    }


}