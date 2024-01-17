package com.assignment.baraq.controller;

import com.assignment.baraq.Model.Buyer;
import com.assignment.baraq.ServiceImpl.BuyerService;
import jakarta.annotation.Resource;
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
public class BuyerController {

  private static final String CREATE_BUYER = "_create_buyer";
  private static final String GET_BUYER = "_get_buyer";
  private static final String DELETE_BUYER = "_delete_buyer";

  @Autowired
  private BuyerService buyerService;


  @PostMapping(value = CREATE_BUYER, produces = MediaType.APPLICATION_JSON_VALUE)
  public Long createBuyer(@RequestParam String buyerName, @RequestParam String buyerAddress) {
    return buyerService.createBuyer(buyerName, buyerAddress);
  }

  @GetMapping(value = GET_BUYER, produces = MediaType.APPLICATION_JSON_VALUE)
  public Buyer getBuyer(@RequestParam Long buyerId) {
    return buyerService.getBuyer(buyerId);
  }

  @DeleteMapping(value = DELETE_BUYER, produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteBuyer(@RequestParam Long buyerId) throws Exception {
    buyerService.deleteBuyer(buyerId);
  }
}
