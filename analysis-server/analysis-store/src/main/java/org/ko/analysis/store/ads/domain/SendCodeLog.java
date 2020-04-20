package org.ko.analysis.store.ads.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.ko.analysis.store.bean.BasicEntity;

/**
 * <p>验证码日志</p>
 * @author K.O
 */
@Data
@TableName("t_send_code_log")
@EqualsAndHashCode(callSuper = true)
public class SendCodeLog extends BasicEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 接收方
     */
    private String receiveAddress;

    /**
     * 发送消息类型
     */
    private String sendType;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 消息代码
     */
    private String messageCode;

    /**
     * 失效时间，单位秒
     */
    private Long expireIn;

    /**
     * 逻辑删除状态
     */
    private short disable;


}