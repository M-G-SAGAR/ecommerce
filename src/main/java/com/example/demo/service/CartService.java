package com.example.demo.service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired private CartRepository cartRepo;
    @Autowired private CartItemRepository cartItemRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private UserRepository userRepo;

    public Cart getCart(String username) {
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return cartRepo.findByUser(user).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepo.save(cart);
        });
    }
    
    public Cart addItemToCart(String username, Long itemId, int quantity) {
        Cart cart = getCart(username); // Existing or new saved cart

        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItemRepo.save(cartItem);

        cart.getItems().add(cartItem);
        cart.setTotal(cart.getItems().stream()
            .mapToDouble(i -> i.getItem().getPrice() * i.getQuantity())
            .sum());

        return cartRepo.save(cart);
    }


    public void removeItemFromCart(String username, Long cartItemId) {
        Cart cart = getCart(username);

        cart.getItems().removeIf(i -> i.getId().equals(cartItemId));
        cartItemRepo.deleteById(cartItemId);

        cart.setTotal(cart.getItems().stream()
                .mapToDouble(i -> i.getItem().getPrice() * i.getQuantity())
                .sum());

        cartRepo.save(cart);
    }

    public Cart checkout(String username) {
        Cart cart = getCart(username);

        cart.getItems().clear();
        cart.setTotal(0);

        return cartRepo.save(cart);
    }
    
    
    //Method for Dto
    public CartDto convertToDto(Cart cart) {
        List<CartItemDto> itemDtos = cart.getItems().stream().map(ci -> new CartItemDto(
                ci.getItem().getId(),
                ci.getItem().getName(),
                ci.getItem().getPrice(),
                ci.getQuantity()
        )).toList();

        return new CartDto(
                cart.getId(),
                cart.getUser().getUsername(),
                itemDtos,
                cart.getTotal()
        );
    }
}
