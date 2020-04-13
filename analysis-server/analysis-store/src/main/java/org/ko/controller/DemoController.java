package org.ko.controller;

import org.ko.order.domain.Order;
import org.ko.order.service.OrderService;
import org.ko.pay.PayService;
import org.ko.user.domain.User;
import org.ko.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
@RestController
public class DemoController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService   payService;


    @GetMapping( "users-tx" )
    public ResponseEntity<User> saveUser () {
        User user = new User();
        user.setEmail("yujunhao_8831@yahoo.com");
        user.setUsername(RandomStringUtils.randomNumeric(1, 10));
        user.setPassword(RandomStringUtils.randomNumeric(1, 10));
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping( "users" )
    public ResponseEntity< List< User > > listUser () {
        return ResponseEntity.ok( userService.list() );
    }

    @GetMapping( "orders" )
    public ResponseEntity< List<Order> > listOrder () {
        return ResponseEntity.ok( orderService.list() );
    }

    @GetMapping( "pays" )
    public ResponseEntity< List< String > > listPay () {
        return ResponseEntity.ok( payService.list() );
    }

}
