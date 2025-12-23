package com.example.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.models.CartItem;
import com.example.main.models.Product;
import com.example.main.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    // Find all items in a specific user's cart
    List<CartItem> findByUser(User user);
    
    // Check if a specific product is already in a specific user's cart
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    
    // Optional: Clear cart after order is placed
    void deleteByUser(User user);
}