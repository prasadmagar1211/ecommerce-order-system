package com.example.main.repositories;

import com.example.main.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // This allows us to find orders for a specific user if needed later
    List<Order> findByUserId(Long userId);
}
