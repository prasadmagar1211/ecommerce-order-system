package com.example.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.models.CartItem;
import com.example.main.models.Product;
import com.example.main.models.User;
import com.example.main.repositories.CartItemRepository;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(User user, Product product, int quantity) {
        // Check if the product is already in this user's cart
        Optional<CartItem> existingItem = cartItemRepository.findByUserAndProduct(user, product);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }
}
