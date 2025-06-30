package com.example.demo.mapper;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.Category;
import com.example.demo.model.Item;

public class ItemMapper {

    public static ItemDto toDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        if (item.getCategory() != null) {
            dto.setCategoryId(item.getCategory().getId());
        }
        return dto;
    }

    public static Item toEntity(ItemDto dto, Category category) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCategory(category);
        return item;
    }
}
