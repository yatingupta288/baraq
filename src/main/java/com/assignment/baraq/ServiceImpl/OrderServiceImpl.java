package com.assignment.baraq.ServiceImpl;

import com.assignment.baraq.DAO.BuyerRepo;
import com.assignment.baraq.DAO.OrderRepo;
import com.assignment.baraq.DAO.ProductRepo;
import com.assignment.baraq.Model.Buyer;
import com.assignment.baraq.Model.Order;
import com.assignment.baraq.Model.Product;
import com.assignment.baraq.Service.OrderService;
import com.assignment.baraq.Service.PinCodeServiceAbilityService;
import com.assignment.baraq.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private ProductRepo productRepo;

  @Autowired
  private BuyerRepo buyerRepo;

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  private ProductService productService;

  @Autowired
  private PinCodeServiceAbilityService pinCodeServiceAbilityService;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Long createOrder(Long buyerId, Long productId, int quantity, String paymentMode) {

    Product product = getProduct(productId);
    Buyer buyer = getBuyer(buyerId);

    validateOrder(product, buyer, quantity, paymentMode);

    Order newOrder = new Order();
    newOrder.setPaymentMode(paymentMode);
    newOrder.setQuantity(quantity);
    newOrder.setProduct(product);
    newOrder.setBuyer(buyer);
    orderRepo.save(newOrder);

    updateProductInventory(product, quantity);

    return newOrder.getId();
  }

  private Product getProduct(Long productId) {
    return productRepo.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
  }

  private Buyer getBuyer(Long buyerId) {
    return buyerRepo.findById(buyerId)
        .orElseThrow(() -> new RuntimeException("Buyer not found with id: " + buyerId));
  }

  private void validateOrder(Product product, Buyer buyer, int quantity, String paymentMode) {
    if (!pinCodeServiceAbilityService.isServiceAble(product.getPickUpAddress(),
        buyer.getBuyerAddress(), paymentMode)) {
      throw new RuntimeException("Order failed because pincode is unserviceable");
    }

    if (product.getInventory() < quantity) {
      throw new RuntimeException("Order failed because product stock is insufficient");
    }
  }

  private void updateProductInventory(Product product, int quantity) {
    product.setInventory(product.getInventory() - quantity);
    productRepo.save(product);
  }

  @Override
  public Order getOrder(Long orderId) {
    return orderRepo.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void deleteOrder(Long orderId) {
    Order order = orderRepo.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

    orderRepo.delete(order);
  }
}
