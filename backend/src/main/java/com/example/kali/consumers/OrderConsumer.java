package com.example.kali.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kali.models.Order;

@Service
public class OrderConsumer {
    
    @KafkaListener(topics = "orders", groupId = "kali-order-group")
    public void consume(Order order) {
        System.out.println("Received order: " + order);
    }
}
