package com.example.kali.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kali.models.Order;
import com.example.kali.stores.OrderStore;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrderStatusConsumer {
    
    private final OrderStore orderStore;

    @KafkaListener (topics = "order-status", groupId = "kali-status-group")
    public void consume(Order order) {
        orderStore.save(order);
        System.out.println("Saved status for order: " + order.getId() + " -> " + order.getStatus());
    }
}
