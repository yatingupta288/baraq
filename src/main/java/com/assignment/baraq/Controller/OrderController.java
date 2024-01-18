package com.assignment.baraq.Controller;

import com.assignment.baraq.Model.Order;
import com.assignment.baraq.Response.OrderResponse;
import com.assignment.baraq.Service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "baraq")
public class OrderController {

  private static final String CREATE_ORDER = "_create_order";
  private static final String GET_ORDER = "_get_order";
  private static final String DELETE_ORDER = "_delete_order";
  @Autowired
  private OrderService orderService;


  @PostMapping(value = CREATE_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> createOrder(@RequestParam Long buyerId,
      @RequestParam Long productId, @RequestParam int quantity, @RequestParam String paymentMode)
      throws Exception {
    Map<String, Object> response = new HashMap<>();
    try {
      Long orderId = orderService.createOrder(buyerId, productId, quantity, paymentMode);
      response.put("success", true);
      response.put("orderId", orderId);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      response.put("success", false);
      response.put("error", "Error creating Order: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping(value = GET_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrderResponse> getOrder(@RequestParam Long orderId) throws Exception {
    Order order = orderService.getOrder(orderId);
    ObjectMapper objectMapper = new ObjectMapper();
    OrderResponse orderResponse = objectMapper.convertValue(order, OrderResponse.class);
    return ResponseEntity.ok(orderResponse);
  }

  @DeleteMapping(value = DELETE_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deleteOrder(@RequestParam Long orderId) throws Exception {
    try {
      orderService.deleteOrder(orderId);
      return ResponseEntity.ok("Order deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Order");
    }
  }
}
