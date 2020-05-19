package org.ko.analysis.store.master.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.store.bean.BasicEntity;

/**
 * <p>用户表，维度表</p>
 * @author K.O
 */
@Data
@TableName("t_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像相对地址
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 生日
     */
    private java.util.Date birthday;

    /**
     * 性别
     */
    private short gender;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信三方登陆id
     */
    private String wechat;

    /**
     * qq三方登陆id
     */
    private String qq;

    /**
     * 微博三方登陆id
     */
    private String weibo;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 余额
     */
    private java.math.BigDecimal balance;


}