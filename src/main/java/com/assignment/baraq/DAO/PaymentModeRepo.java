package com.assignment.baraq.DAO;

import com.assignment.baraq.Model.PaymentMode;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface PaymentModeRepo extends JpaRepository<PaymentMode, Long> {}
