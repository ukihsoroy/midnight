package ${rootPackage}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.sigma.data.entity.${entityName};
import ${rootPackage}.condition.Query${entityName}Condition;

import java.util.List;

/**
 * ${comment}
 */
public interface ${entityName}Service extends IService<${entityName}> {

    /**
     * <p>查询${comment}列表</p>
     * @param condition
     * @return
     */
    List<${entityName}> query${entityName}List(Query${entityName}Condition condition);

    /**
     * <p>通过主键查询${comment}详细信息</p>
     * @param id
     * @return
     */
    ${entityName} query${entityName}Info(Long id);

    /**
     * <p>新增${comment}一条数据</p>
     * @param ${entityName?uncap_first}
     */
    Long create${entityName}(${entityName} ${entityName?uncap_first});

    /**
     * <p>通过ID更新${comment}</p>
     * @param id ${entityName} Id
     * @param ${entityName?uncap_first}
     * @return
     */
    ${entityName} update${entityName}(Long id, ${entityName} ${entityName?uncap_first});

    /**
     * <p>删除${comment}数据</p>
     * @param id ${entityName} 主键id
     * @return
     */
    Long delete${entityName}(Long id);

}