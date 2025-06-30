package com.example.demo.dto;

import lombok.*;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotEmpty(message = "Cart must have at least one item")
    private List<CartItemDto> items;
    
    @PositiveOrZero(message = "Total must be zero or more")
    private double total;
}

