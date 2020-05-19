package org.ko.analysis.store.master.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ko.analysis.store.bean.BasicEntity;

/**
 * <p>字典表</p>
 * @author K.O
 */
@Data
@Builder
@TableName("t_dict")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Dict extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String value;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}