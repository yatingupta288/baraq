package com.assignment.baraq.DAO;

import com.assignment.baraq.Model.Product;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface ProductRepo extends JpaRepository<Product, Long> {

  Optional<Product> findByProductName(String productName);

  Optional<Product> findByProductNameAndPickUpAddress(String productName, String pickUpAddress);
}
