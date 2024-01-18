package com.assignment.baraq.controller;

import com.assignment.baraq.Model.Buyer;
import com.assignment.baraq.Response.BuyerResponse;
import com.assignment.baraq.ServiceImpl.BuyerService;
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
public class BuyerController {

  private static final String CREATE_BUYER = "_create_buyer";
  private static final String GET_BUYER = "_get_buyer";
  private static final String DELETE_BUYER = "_delete_buyer";

  @Autowired
  private BuyerService buyerService;


  @PostMapping(value = CREATE_BUYER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> createBuyer(@RequestParam String buyerName,
      @RequestParam String buyerAddress) {
    Map<String, Object> response = new HashMap<>();
    try {
      Long createdBuyerId = buyerService.createBuyer(buyerName, buyerAddress);
      response.put("success", true);
      response.put("BuyerId", createdBuyerId);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      response.put("success", false);
      response.put("error", "Error creating Buyer: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

  }

  @GetMapping(value = GET_BUYER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BuyerResponse> getBuyer(@RequestParam Long buyerId) {
    Buyer buyer = buyerService.getBuyer(buyerId);
    ObjectMapper objectMapper = new ObjectMapper();
    BuyerResponse buyerResponse = objectMapper.convertValue(buyer, BuyerResponse.class);
    return ResponseEntity.ok(buyerResponse);
  }

  @DeleteMapping(value = DELETE_BUYER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deleteBuyer(@RequestParam Long buyerId) {
    try {
      buyerService.deleteBuyer(buyerId);
      return ResponseEntity.ok("Buyer deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting buyer");
    }
  }

}
