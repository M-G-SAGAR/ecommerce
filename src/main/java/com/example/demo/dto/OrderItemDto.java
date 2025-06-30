package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDto {
	
	@NotNull(message = "Item ID is required")
    private Long itemId;
    private String itemName;
    private double price;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}

