package com.assignment.baraq.ServiceImpl;

import com.assignment.baraq.Model.Product;

public interface ProductService {
  Long createProduct(String productName, int inventory, int price, String pickUpAddress);

  Product getProduct(String productName);

  void deleteProduct(String productName);
}
