package org.ko.analysis.store.ads.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.ko.analysis.store.bean.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>字典表</p>
 * @author K.O
 */
@Data
@Builder
@TableName("t_dict")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
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