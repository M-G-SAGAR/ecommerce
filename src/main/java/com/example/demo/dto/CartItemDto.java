package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long itemId;
    private String itemName;
    private double price;
    private int quantity;
}

