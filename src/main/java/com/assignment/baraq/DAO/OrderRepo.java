package com.assignment.baraq.DAO;

import com.assignment.baraq.Model.Order;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface OrderRepo extends JpaRepository<Order, Long> {}
