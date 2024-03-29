package com.assignment.baraq.Service;

import com.assignment.baraq.Model.Buyer;

public interface BuyerService {
  Long createBuyer(String buyerName, String address);

  Buyer getBuyer(Long buyerId);

  void deleteBuyer(Long buyerId) throws Exception;
}
