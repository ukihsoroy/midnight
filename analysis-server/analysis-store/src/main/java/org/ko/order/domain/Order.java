package org.ko.order.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
@TableName( "aidijing_order" )
public class Order extends Model< Order > {

    @TableId( value = "id", type = IdType.AUTO )
    private Long   id;
    @TableField( "order_id" )
    private String orderId;
    @TableField( "order_name" )
    private String orderName;

    @Override
    protected Serializable pkVal () {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
