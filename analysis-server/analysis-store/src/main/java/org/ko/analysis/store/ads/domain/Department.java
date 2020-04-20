package org.ko.analysis.store.ads.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.store.bean.BasicEntity;


/**
 * <p>部门表</p>
 * @author K.O
 */
@Data
@TableName("t_department")
@EqualsAndHashCode(callSuper = true)
public class Department extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门编码，由系统统一生成，不可修改
     */
    private String code;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级部门编码
     */
    private String parentCode;


}