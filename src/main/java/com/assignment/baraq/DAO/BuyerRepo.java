package com.assignment.baraq.DAO;

import com.assignment.baraq.Model.Buyer;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface BuyerRepo extends JpaRepository<Buyer, Long> {}
