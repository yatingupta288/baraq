package com.assignment.baraq.controller;

import com.assignment.baraq.Model.Product;
import com.assignment.baraq.Response.ProductReponse;
import com.assignment.baraq.ServiceImpl.ProductService;
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
public class ProductController {

  private static final String GET_PRODUCT = "_get_product";
  private static final String CREATE_PRODUCT = "_create_product";
  private static final String DELETE_PRODUCT = "_delete_product";
  @Autowired
  private ProductService productService;


  @PostMapping(value = CREATE_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> createOrder(@RequestParam String productName,
      @RequestParam int inventory, @RequestParam int price, @RequestParam String pickUpAddress)
      throws Exception {
    Map<String, Object> response = new HashMap<>();
    try {
      Long productId = productService.createProduct(productName, inventory, price, pickUpAddress);
      response.put("success", true);
      response.put("productId", productId);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      response.put("success", false);
      response.put("error", "Error creating Product: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @GetMapping(value = GET_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductReponse> getOrder(@RequestParam String productName)
      throws Exception {
    Product product = productService.getProduct(productName);
    ObjectMapper objectMapper = new ObjectMapper();
    ProductReponse productReponse = objectMapper.convertValue(product, ProductReponse.class);
    return ResponseEntity.ok(productReponse);
  }

  @DeleteMapping(value = DELETE_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deleteOrder(@RequestParam String productName) throws Exception {
    try {
      productService.deleteProduct(productName);
      return ResponseEntity.ok("Product deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Product");
    }
  }
}
