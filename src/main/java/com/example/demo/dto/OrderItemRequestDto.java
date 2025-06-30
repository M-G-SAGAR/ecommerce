package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderItemRequestDto {
    private Long itemId;
    private int quantity;
}

