package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Data
public class OrderDto {
    private Long id;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private LocalDateTime orderDate;
    
    @PositiveOrZero(message = "Total amount cannot be negative")
    private double totalAmount;
    
    @NotEmpty(message = "Order must have at least one item")
    private List<@Valid OrderItemDto> items;
}

