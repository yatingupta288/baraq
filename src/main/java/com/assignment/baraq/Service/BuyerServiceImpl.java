package com.assignment.baraq.Service;

import com.assignment.baraq.DAO.BuyerRepo;
import com.assignment.baraq.Model.Buyer;
import com.assignment.baraq.ServiceImpl.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

  @Autowired
  private BuyerRepo buyerRepo;

  @Override
  public Long createBuyer(String buyerName, String address) {
    Buyer buyer = Buyer.builder().buyerName(buyerName).buyerAddress(address).build();
    buyerRepo.save(buyer);
    return buyer.getId();
  }

  @Override
  public Buyer getBuyer(Long buyerId) {
    Optional<Buyer> buyer = buyerRepo.findById(buyerId);
    return buyer.orElse(null);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void deleteBuyer(Long buyerId) throws Exception {
    Optional<Buyer> buyer = buyerRepo.findById(buyerId);
    if (buyer.isEmpty()) {
      throw new RuntimeException("Buyer is not present");
    } else {
      buyerRepo.delete(buyer.get());
    }
  }
}
