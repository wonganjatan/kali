package com.example.kali.models;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Order {
    private String id;
    private String item;
    private int quantity;
    private String status;
    private Instant createdAt;
}