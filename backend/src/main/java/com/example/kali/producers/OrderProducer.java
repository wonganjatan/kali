package com.example.kali.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kali.models.Order;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrderProducer {
    
    private final KafkaTemplate<String, Order> kafkaTemplate;
    
    private static final String TOPIC = "orders";

    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.getId(), order);
        System.out.println("Produed order: " + order.getId());
    }
}
