package com.sylinx.springbootcache.order.service;

import com.sylinx.springbootcache.order.entity.Order;
import com.sylinx.springbootcache.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> findOrderById(Integer id){
        return this.orderRepository.findOrderById(id);
    }

    public Order updateOrder(Order person){
        return this.orderRepository.updateOrder(person);
    }

    public Optional<Order> deleteOrder(Integer id){
        return this.orderRepository.deleteOrder(id);
    }

    public boolean insertOrder(Order person){
        return this.orderRepository.insertOrder(person);
    }
}
