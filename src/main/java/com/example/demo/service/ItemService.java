package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepo;

	public List<Item> getAllItems() {
		return itemRepo.findAll();
	}

	public Item getItem(Long id) {
		return itemRepo.findById(id).orElse(null);
	}

	public Item saveItem(Item item) {
		return itemRepo.save(item);
	}

	public Item updateItem(Long id, Item item) {
		item.setId(id);
		return itemRepo.save(item);
	}

	public void deleteItem(Long id) {
		itemRepo.deleteById(id);
	}
	
	
	//Item Search By Category_ID
	public List<Item> getItemsByCategoryId(Long id) {
	    return itemRepo.findByCategoryId(id);
	}
	
	//Item Search By Category_NAME
	public List<Item> getItemsByCategoryName(String name) {
	    return itemRepo.findByCategory_Name(name);
	}
	
	//Item Search By Category
	public List<Item> getItemsByCategory(Category category) {
	    return itemRepo.findByCategory(category);
	}

}
