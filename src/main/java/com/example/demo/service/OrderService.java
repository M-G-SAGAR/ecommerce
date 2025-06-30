package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderItemDto;
import com.example.demo.dto.OrderItemRequestDto;
import com.example.demo.dto.OrderRequestDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ItemRepository itemRepo;

	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	public Order getOrder(Long id) {
		return orderRepo.findById(id).orElse(null);
	}

	public Order saveOrder(Order order) {
		return orderRepo.save(order);
	}

	public Order updateOrder(Long id, Order order) {
		order.setId(id);
		return orderRepo.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepo.deleteById(id);
	}


	    public Order placeOrder(OrderRequestDto request) {
	        User user = userRepo.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	        Order order = new Order();
	        order.setUser(user);
	        order.setOrderDate(LocalDateTime.now());

	        List<OrderItem> orderItems = new ArrayList<>();
	        double total = 0;

	        for (OrderItemRequestDto dto : request.getItems()) {
	            Item item = itemRepo.findById(dto.getItemId())
	                .orElseThrow(() -> new RuntimeException("Item not found"));

	            OrderItem orderItem = new OrderItem();
	            orderItem.setItem(item);
	            orderItem.setOrder(order);
	            orderItem.setQuantity(dto.getQuantity());

	            double price = item.getPrice() * dto.getQuantity();
	            orderItem.setPrice(price);

	            total += price;
	            orderItems.add(orderItem);
	        }

	        order.setItems(orderItems);
	        order.setPrice(total);

	        return orderRepo.save(order);
	        
	    }
	


}
