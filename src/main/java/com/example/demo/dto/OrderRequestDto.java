package com.example.demo.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
	
	@NotNull(message = "User ID is required")
    private Long userId;
	
	@NotEmpty(message = "Order items cannot be empty")
    @Valid
    private List<OrderItemRequestDto> items;
}

