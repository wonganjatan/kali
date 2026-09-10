package com.example.kali.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kali.models.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class OrderConsumer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    private static final String STATUS_TOPIC = "order-status";
    
    @KafkaListener(topics = "orders", groupId = "kali-order-group")
    public void consume(Order order) throws InterruptedException {
        System.out.println("Processing order: " + order.getId());

        Thread.sleep(3000);

        order.setStatus("COMPLETED");

        kafkaTemplate.send(STATUS_TOPIC, order.getId(), order);
        System.out.println("Order completed: " + order.getId());
    }
}
