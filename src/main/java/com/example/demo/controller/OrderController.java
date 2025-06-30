package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<Order> getOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
		return orderService.getOrder(id);
	}

	@PostMapping
	public Order createOrder(@RequestBody Order order) {
		return orderService.saveOrder(order);
	}

	@PutMapping("/{id}")
	public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
		return orderService.updateOrder(id, order);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	@PostMapping("/order")
	public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequestDto request) {
	    Order order = orderService.placeOrder(request);
	    return ResponseEntity.ok(order);
	}

}
