package com.assignment.baraq.controller;

import com.assignment.baraq.Model.PinCodeServiceAbility;
import com.assignment.baraq.ServiceImpl.PinCodeServiceAbilityService;
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
public class PinCodeServiceAbilityController {

  private static final String CREATE_SERVICE_ABILITY = "_create_serviceAbility";
  private static final String GET_SERVICE_ABILITY = "_get_serviceAbility";
  private static final String DELETE_SERVICE_ABILITY = "_delete_serviceAbility";
  @Autowired
  private PinCodeServiceAbilityService pinCodeServiceAbilityService;


  @PostMapping(value = CREATE_SERVICE_ABILITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public void createPinCodeServiceAbility(@RequestParam String sourcePincode,
      @RequestParam String destinationPincode, @RequestParam String paymentMode) throws Exception {
    pinCodeServiceAbilityService.createPinCodeServiceAbility(sourcePincode, destinationPincode,
        paymentMode);
  }

  @GetMapping(value = GET_SERVICE_ABILITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public PinCodeServiceAbility getPinCodeServiceAbility(@RequestParam String sourcePincode,
      @RequestParam String destinationPincode) throws Exception {
    return pinCodeServiceAbilityService.getPinCodeServiceAbility(sourcePincode, destinationPincode);
  }

  @DeleteMapping(value = DELETE_SERVICE_ABILITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public void deletePinCodeServiceAbility(@RequestParam String sourcePincode,
      @RequestParam String destinationPincode) throws Exception {
    pinCodeServiceAbilityService.deletePinCodeServiceAbility(sourcePincode, destinationPincode);
  }
}
