package com.assignment.baraq.Service;

import com.assignment.baraq.DAO.PaymentModeRepo;
import com.assignment.baraq.DAO.PincodeServiceAbilityRepo;
import com.assignment.baraq.Model.PaymentMode;
import com.assignment.baraq.Model.PinCodeServiceAbility;
import com.assignment.baraq.ServiceImpl.PinCodeServiceAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PinCodeServiceAbilityServiceImpl implements PinCodeServiceAbilityService {

  @Autowired
  private PincodeServiceAbilityRepo pincodeServiceAbilityRepo;

  @Autowired
  private PaymentModeRepo paymentModeRepo;

  public boolean isServiceAble(String sourcePinCode, String destinationPinCode,
      String paymentMode) {
    return pincodeServiceAbilityRepo.getBySourcePinCodeAndDestinationPinCodeAndPaymentModes(
        sourcePinCode, destinationPinCode, paymentMode);
  }

  public void createPinCodeServiceAbility(String sourcePinCode, String destinationPinCode,
      String paymentMode) {
    PinCodeServiceAbility pinCodeServiceAbility =
        getOrCreatePinCodeServiceAbility(sourcePinCode, destinationPinCode);

    PaymentMode paymentMode1 =
        PaymentMode.builder().pinCodeServiceAbility(pinCodeServiceAbility).paymentMode(paymentMode)
            .build();

    paymentModeRepo.save(paymentMode1);
  }


  @Override
  public PinCodeServiceAbility getPinCodeServiceAbility(String sourcePinCode,
      String destinationPinCode) {
    return isAlreadyPresent(sourcePinCode, destinationPinCode).orElseThrow(
        () -> new RuntimeException(
            "Mapping does not exist for these pincodes " + sourcePinCode + " "
                + destinationPinCode));
  }

  @Override
  public void deletePinCodeServiceAbility(String sourcePinCode, String destinationPinCode) {
    PinCodeServiceAbility pinCodeServiceAbility =
        isAlreadyPresent(sourcePinCode, destinationPinCode).orElseThrow(() -> new RuntimeException(
            "Mapping does not exist for these pincodes " + sourcePinCode + " "
                + destinationPinCode));

    pincodeServiceAbilityRepo.delete(pinCodeServiceAbility);
  }

  private PinCodeServiceAbility getOrCreatePinCodeServiceAbility(String sourcePinCode,
      String destinationPinCode) {
    return isAlreadyPresent(sourcePinCode, destinationPinCode).orElseGet(() -> {
      PinCodeServiceAbility newAbility = new PinCodeServiceAbility();
      newAbility.setSourcePinCode(sourcePinCode);
      newAbility.setDestinationPinCode(destinationPinCode);
      return pincodeServiceAbilityRepo.save(newAbility);
    });
  }

  private Optional<PinCodeServiceAbility> isAlreadyPresent(String sourcePinCode,
      String destinationPinCode) {
    return pincodeServiceAbilityRepo.findBySourcePinCodeAndDestinationPinCode(sourcePinCode,
        destinationPinCode);
  }
}

