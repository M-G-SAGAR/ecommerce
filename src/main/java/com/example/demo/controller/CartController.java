package com.example.demo.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartDto;
import com.example.demo.model.Cart;
import com.example.demo.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired private CartService cartService;

//    @GetMapping
//    public Cart viewCart(Authentication auth) {
//        return cartService.getCart(auth.name());
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<?> addItemToCart(
//        @RequestParam Long itemId,
//        @RequestParam int quantity,
//        @RequestParam("username") String username,   // 👈 यहाँ rename किया है
//        @RequestHeader("Authorization") String token
//    ) {
//        Cart updatedCart = cartService.addItemToCart(username, itemId, quantity);
//        return ResponseEntity.ok(updatedCart);
//    }
    
    //Method For DTo
    @GetMapping
    public ResponseEntity<CartDto> viewCart(@Valid @RequestParam("username") String username) {
        Cart cart = cartService.getCart(username);
        CartDto dto = cartService.convertToDto(cart);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> addItemToCart(
            @RequestParam Long itemId,
            @RequestParam int quantity,
            @RequestParam("username") String username,
            @RequestHeader("Authorization") String token
    ) {
        Cart updatedCart = cartService.addItemToCart(username, itemId, quantity);
        return ResponseEntity.ok(cartService.convertToDto(updatedCart));
    }

    @DeleteMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id, Authentication auth) {
        cartService.removeItemFromCart(auth.name(), id);
    }

    @PostMapping("/checkout")
    public Cart checkout(Authentication auth) {
        return cartService.checkout(auth.name());
    }
}

