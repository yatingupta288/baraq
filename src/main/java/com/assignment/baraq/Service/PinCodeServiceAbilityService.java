package com.assignment.baraq.Service;

import com.assignment.baraq.Model.PinCodeServiceAbility;

public interface PinCodeServiceAbilityService {

  boolean isServiceAble(String sourcePinCode, String destinationPinCode, String paymentMode);

  void createPinCodeServiceAbility(String sourcePinCode, String destinationPinCode,
      String paymentMode);

  PinCodeServiceAbility getPinCodeServiceAbility(String sourcePinCode, String destinationPinCode);

  void deletePinCodeServiceAbility(String sourcePinCode, String destinationPinCode);
}
