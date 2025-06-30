package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
    List<Item> findByNameContaining(String name);
    
    //List<Item> findByCategory(String category);
    
    List<Item> findByCategory(Category category);

    // By category id
    List<Item> findByCategoryId(Long id);

    // By category name
    List<Item> findByCategory_Name(String name);
    
    List<Item> findByPrice(double price);
}
