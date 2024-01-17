package com.assignment.baraq.Service;

import com.assignment.baraq.DAO.ProductRepo;
import com.assignment.baraq.Model.Product;
import com.assignment.baraq.ServiceImpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepo productRepo;

  @Override
  public Long createProduct(String productName, int inventory, int price, String pickUpAddress) {
    Product product =
        Product.builder().productName(productName).price(price).pickUpAddress(pickUpAddress)
            .inventory(inventory).build();
    productRepo.save(product);
    return product.getId();

  }

  @Override
  public Product getProduct(String productName) {
    Optional<Product> product = productRepo.findByProductName(productName);
    if (product.isPresent()) {
      return product.get();
    } else {
      throw new RuntimeException("no such product exists");
    }
  }

  @Override
  public void deleteProduct(String productName) {
    Optional<Product> product = productRepo.findByProductName(productName);
    if (product.isPresent()) {
      productRepo.delete(product.get());
    } else {
      throw new RuntimeException("no such product exists");
    }
  }
}
