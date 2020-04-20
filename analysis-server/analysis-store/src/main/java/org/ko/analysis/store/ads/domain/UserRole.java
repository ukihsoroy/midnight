package org.ko.analysis.store.ads.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.ko.analysis.store.bean.BasicEntity;

/**
 * <p>用户权限关联表，事实表</p>
 * @author K.O
 */
@Data
@TableName("t_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户编码（UUID），由系统统一生成，不可修改
     */
    private Long userId;

    /**
     * 角色编码，由系统统一生成，不可修改
     */
    private String roleCode;


}