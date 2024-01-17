package com.assignment.baraq.controller;

import com.assignment.baraq.Model.Order;
import com.assignment.baraq.ServiceImpl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "baraq")
public class OrderController {

  private static final String CREATE_ORDER = "_create_order";
  private static final String GET_ORDER = "_get_order";
  private static final String DELETE_ORDER = "_delete_order";
  @Autowired
  private OrderService orderService;


  @PostMapping(value = CREATE_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public Long createOrder(@RequestParam Long buyerId, @RequestParam Long productId,
      @RequestParam int quantity, @RequestParam String paymentMode) throws Exception {
    return orderService.createOrder(buyerId, productId, quantity, paymentMode);
  }

  @GetMapping(value = GET_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public Order getOrder(@RequestParam Long orderId) throws Exception {
    return orderService.getOrder(orderId);
  }

  @DeleteMapping(value = DELETE_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteOrder(@RequestParam Long orderId) throws Exception {
    orderService.deleteOrder(orderId);
  }
}
