package com.assignment.baraq.DAO;

import com.assignment.baraq.Model.Order;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface OrderRepo extends JpaRepository<Order, Long> {
  Optional<Order> findByBuyerIdAndProductId(Long buyerId, Long productId);
}
