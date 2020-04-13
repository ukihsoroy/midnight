package org.ko.order.service;

import org.ko.order.domain.Order;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
public interface OrderService extends IService< Order > {
    
    List<Order> list ();
}
