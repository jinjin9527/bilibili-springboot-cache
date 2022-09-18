package com.sylinx.springbootcache.order.repository;

import com.sylinx.springbootcache.order.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderRepository {

    private static List<Order> BASE_ORDER_LIST = new ArrayList<>();

    public OrderRepository(){
        Order order1 = Order.builder().id(1).delivery("time1").address("address1").build();
        Order order2 = Order.builder().id(2).delivery("time1").address("address2").build();
        Order order3 = Order.builder().id(3).delivery("time1").address("address3").build();
        BASE_ORDER_LIST.add(order1);
        BASE_ORDER_LIST.add(order2);
        BASE_ORDER_LIST.add(order3);
    }

    public boolean insertOrder(Order order){
        boolean exists = BASE_ORDER_LIST.stream().anyMatch(streamOrder -> streamOrder.getId() == order.getId());
        if(!exists) {
            BASE_ORDER_LIST.add(order);
        }
        return !exists;
    }


    public Order updateOrder(Order order){
        Optional<Order> result = findOrderById(order.getId());
        result.ifPresent(updateTarget -> updateTarget
                .setDelivery(order.getDelivery())
                .setAddress(order.getAddress())
        );
        return result.orElse(null);
    }

    public  Optional<Order> deleteOrder(Integer id){
        Optional<Order> result = findOrderById(id);
        result.ifPresent(order -> BASE_ORDER_LIST.remove(order));
        return result;
    }

    public Optional<Order> findOrderById(Integer id){
        return BASE_ORDER_LIST.stream().filter(order -> order.getId() == id).findAny();
    }
}
