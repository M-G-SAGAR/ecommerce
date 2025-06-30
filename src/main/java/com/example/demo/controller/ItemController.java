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

import com.example.demo.dto.ItemDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.Category;
import com.example.demo.model.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ItemRepository itemRepo;

	@GetMapping
	public List<Item> getItems() {
		return itemService.getAllItems();
	}

	@GetMapping("/{id}")
	public Item getItem(@PathVariable Long id) {
		return itemService.getItem(id);
	}

	@PostMapping("/items")
	public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
	    Category category = categoryRepo.findById(itemDto.getCategoryId())
	            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

	    Item item = ItemMapper.toEntity(itemDto, category);
	    Item savedItem = itemRepo.save(item);
	    return ResponseEntity.ok(ItemMapper.toDto(savedItem));
	}

	@PutMapping("/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
		return itemService.updateItem(id, item);
	}

	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
	}
	
	//Category Search by Id
	@GetMapping("/category/{id}")
	public List<Item> getItemsByCategory(@PathVariable Long id) {
	    return itemService.getItemsByCategoryId(id);
	}

}
