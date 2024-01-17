package com.assignment.baraq.controller;

import com.assignment.baraq.Model.Product;
import com.assignment.baraq.ServiceImpl.ProductService;
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
public class ProductController {

  private static final String GET_PRODUCT = "_get_product";
  private static final String CREATE_PRODUCT = "_create_product";
  private static final String DELETE_PRODUCT = "_delete_product";
  @Autowired
  private ProductService productService;


  @PostMapping(value = CREATE_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public Long createOrder(@RequestParam String productName, @RequestParam int inventory,
      @RequestParam int price, @RequestParam String pickUpAddress) throws Exception {
    return productService.createProduct(productName, inventory, price, pickUpAddress);
  }

  @GetMapping(value = GET_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public Product getOrder(@RequestParam String productName) throws Exception {
    return productService.getProduct(productName);
  }

  @DeleteMapping(value = DELETE_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteOrder(@RequestParam String productName) throws Exception {
    productService.deleteProduct(productName);
  }
}
