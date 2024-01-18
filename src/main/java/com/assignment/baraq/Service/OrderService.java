package com.assignment.baraq.Service;

import com.assignment.baraq.Model.Order;

public interface OrderService {
  Long createOrder(Long buyerId, Long productId, int quantity, String paymentMode) throws Exception;

  Order getOrder(Long orderId) throws Exception;

  void deleteOrder(Long orderId) throws Exception;
}
