package com.assignment.baraq.Controller;

import com.assignment.baraq.Model.PinCodeServiceAbility;
import com.assignment.baraq.Response.PinCodeServiceAbilityResponse;
import com.assignment.baraq.ServiceImpl.PinCodeServiceAbilityService;
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

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "baraq")
public class PinCodeServiceAbilityController {

  private static final String CREATE_SERVICE_ABILITY = "_create_serviceAbility";
  private static final String GET_SERVICE_ABILITY = "_get_serviceAbility";
  private static final String DELETE_SERVICE_ABILITY = "_delete_serviceAbility";
  @Autowired
  private PinCodeServiceAbilityService pinCodeServiceAbilityService;


  @PostMapping(value = CREATE_SERVICE_ABILITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> createPinCodeServiceAbility(
      @RequestParam String sourcePincode, @RequestParam String destinationPincode,
      @RequestParam String paymentMode) {
    try {
      pinCodeServiceAbilityService.createPinCodeServiceAbility(sourcePincode, destinationPincode,
          paymentMode);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(Collections.singletonMap("message", "Pincode mapping done successfully"));
    } catch (Exception e) {
      String errorMessage = "Error creating pincode mapping: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("error", errorMessage));
    }
  }


  @GetMapping(value = GET_SERVICE_ABILITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PinCodeServiceAbilityResponse> getPinCodeServiceAbility(
      @RequestParam String sourcePincode, @RequestParam String destinationPincode)
      throws Exception {
    PinCodeServiceAbility pinCodeServiceAbility =
        pinCodeServiceAbilityService.getPinCodeServiceAbility(sourcePincode, destinationPincode);
    ObjectMapper objectMapper = new ObjectMapper();
    PinCodeServiceAbilityResponse response =
        objectMapper.convertValue(pinCodeServiceAbility, PinCodeServiceAbilityResponse.class);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = DELETE_SERVICE_ABILITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deletePinCodeServiceAbility(@RequestParam String sourcePincode,
      @RequestParam String destinationPincode) throws Exception {
    try {
      pinCodeServiceAbilityService.deletePinCodeServiceAbility(sourcePincode, destinationPincode);
      return ResponseEntity.ok("Mapping deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Mapping");
    }
  }
}
