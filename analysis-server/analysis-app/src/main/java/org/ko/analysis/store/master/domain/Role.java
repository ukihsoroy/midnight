package org.ko.analysis.store.master.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;
import org.ko.analysis.store.bean.BasicEntity;

/**
 * <p>系统角色表，维度表</p>
 * @author K.O
 */
@Data
@TableName("t_role")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Role extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色编码，由系统统一生成，不可修改
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;


}