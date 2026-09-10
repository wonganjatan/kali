package com.example.kali.stores;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.kali.models.Order;

@Component 
public class OrderStore {
    
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Order findById(String id) {
        return orders.get(id);
    }

    public Collection<Order> findAll() {
        return orders.values();
    }
}
