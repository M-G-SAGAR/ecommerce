package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> { 
	
	@Query("SELECT o FROM Order o JOIN o.items i WHERE i.item.category.name = :category")
	List<Order> findByItemCategory(@Param("category") String category);

    List<Order> findByPrice(double price);
}

