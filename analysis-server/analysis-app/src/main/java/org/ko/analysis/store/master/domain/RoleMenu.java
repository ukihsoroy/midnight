package org.ko.analysis.store.master.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.ko.analysis.store.bean.BasicEntity;

/**
 * <p>权限菜单关联表，事实表</p>
 * @author K.O
 */
@Data
@TableName("t_role_menu")
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色编码，由系统统一生成，不可修改
     */
    private String roleCode;

    /**
     * 菜单编码，由系统统一生成，不可修改
     */
    private Long menuId;

    /**
     * 创建数据权限：0-否，1-是
     */
    private short create;

    /**
     * 读取详情权限：0-否，1-是
     */
    private short read;

    /**
     * 更新数据权限：0-否，1-是
     */
    private short update;

    /**
     * 删除数据权限：0-否，1-是
     */
    private short delete;


}