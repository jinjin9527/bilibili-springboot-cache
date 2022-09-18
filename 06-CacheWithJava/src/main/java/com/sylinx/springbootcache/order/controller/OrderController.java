package com.sylinx.springbootcache.order.controller;

import com.sylinx.springbootcache.order.entity.Order;
import com.sylinx.springbootcache.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/findOrder/{id}")
    public Optional<Order> findOrder(@PathVariable Integer id){
        Optional<Order> order = this.orderService.findOrderById(id);
        return order;
    }

    @PostMapping("/insertOrder")
    public String insertOrder(@RequestBody Order order){
        boolean result = this.orderService.insertOrder(order);
        return result?"insert success":"insert fail";
    }

    @PostMapping("/updateOrder")
    public Order updateOrder(@RequestBody Order order){
        Order result = this.orderService.updateOrder(order);
        return result;
    }

    @PostMapping("/deleteOrder/{id}")
    public Optional<Order> deleteOrder(@PathVariable Integer id){
        Optional<Order> result = this.orderService.deleteOrder(id);
        return result;
    }
}
