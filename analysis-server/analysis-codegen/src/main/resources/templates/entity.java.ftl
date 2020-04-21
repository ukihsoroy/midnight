package ${rootPackage}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.sigma.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>${comment}</p>
 * @author K.O
 */
@Data
@TableName("${name}")
@EqualsAndHashCode(callSuper = true)
public class ${entityName} extends BasicEntity {

<#list columns as column>
    /**
     * ${column.comment}
     */
    <#if column.primaryKey>
    @TableId(type = IdType.AUTO)
    </#if>
    private ${column.propertyType} ${column.propertyName};

</#list>

}