package org.ko.analysis.store.mpp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * description: t_produce <br>
 * date: 4/13/2020 18:52 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Data
@TableName("t_produce")
public class Produce {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date created_date;

    private String device_no;

}
