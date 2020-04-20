package org.ko.analysis.store.ads.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.ko.analysis.store.bean.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>部门用户关联表，事实表</p>
 * @author K.O
 */
@Data
@TableName("t_department_user")
@EqualsAndHashCode(callSuper = true)
public class DepartmentUser extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门编码，由系统统一生成，不可修改
     */
    private String departmentCode;

    /**
     * 用户编码（UUID），由系统统一生成，不可修改
     */
    private String userId;


}