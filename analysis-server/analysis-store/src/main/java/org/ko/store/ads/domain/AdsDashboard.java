package org.ko.store.ads.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.store.bean.BasicEntity;

/**
 * description: 操作数据层数据表 <br>
 * date: 4/13/2020 18:27 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Data
@TableName("t_ads_dashboard")
@EqualsAndHashCode(callSuper = true)
public class AdsDashboard extends BasicEntity {

    /**
     * 大屏ID
     */
    @TableId(type = IdType.AUTO)
    private String dashId;

    /**
     * 模块ID
     */
    private String boardId;

    /**
     * 数据
     */
    private String data;

}
