package com.example.kali.controllers;

import java.time.Instant;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kali.models.Order;
import com.example.kali.producers.OrderProducer;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/orders")
@RequiredArgsConstructor 
public class OrderController {
    
    private final OrderProducer orderProducer;

    @PostMapping 
    public ResponseEntity<Order> createOrder(@RequestBody Order incomingOrder) {
        Order order = new Order(
            UUID.randomUUID().toString(),
            incomingOrder.getItem(),
            incomingOrder.getQuantity(),
            "PENDING",
            Instant.now()
        );

        orderProducer.sendOrder(order);

        return ResponseEntity.ok(order);
    }
}
