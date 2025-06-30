package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

	    public static OrderDto toDto(Order order) {
	        OrderDto dto = new OrderDto();
	        dto.setId(order.getId());
	        dto.setUserId(order.getUser().getId());
	        dto.setOrderDate(order.getOrderDate());
	        dto.setTotalAmount(order.getPrice());

	        dto.setItems(order.getItems().stream()
	            .map(OrderMapper::mapOrderItemToDto)
	            .collect(Collectors.toList()));

	        return dto;
	    }

	    public static OrderItemDto mapOrderItemToDto(OrderItem item) {
	        OrderItemDto dto = new OrderItemDto();
	        dto.setItemId(item.getId());
	        dto.setItemName(item.getItem().getName());
	        dto.setQuantity(item.getQuantity());
	        dto.setPrice(item.getPrice());
	        return dto;
	    }
	

}

