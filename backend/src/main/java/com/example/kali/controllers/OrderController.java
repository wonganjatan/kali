package com.example.kali.controllers;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kali.models.Order;
import com.example.kali.producers.OrderProducer;
import com.example.kali.stores.OrderStore;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/orders")
@RequiredArgsConstructor 
public class OrderController {
    
    private final OrderProducer orderProducer;
    private final OrderStore orderStore;

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

    @GetMapping 
    public ResponseEntity<Collection<Order>> getAllOrders() {
        return ResponseEntity.ok(orderStore.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderStore.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }
}
