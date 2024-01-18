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
  private ProductRepo productRepo;

  @Override
  public Long createProduct(String productName, int inventory, int price, String pickUpAddress) {
    Optional<Product> existingProduct =
        productRepo.findByProductNameAndPickUpAddress(productName, pickUpAddress);

    if (existingProduct.isPresent()) {
      Product product = existingProduct.get();
      product.setInventory(inventory);
      product.setPickUpAddress(pickUpAddress);
      return productRepo.save(product).getId();
    } else {
      Product newProduct =
          Product.builder().productName(productName).price(price).pickUpAddress(pickUpAddress)
              .inventory(inventory).build();
      return productRepo.save(newProduct).getId();
    }
  }

  @Override
  public Product getProduct(String productName) {
    return productRepo.findByProductName(productName)
        .orElseThrow(() -> new RuntimeException("No such product exists"));
  }

  @Override
  public void deleteProduct(String productName) {
    Product product = productRepo.findByProductName(productName)
        .orElseThrow(() -> new RuntimeException("No such product exists"));

    productRepo.delete(product);
  }
}
