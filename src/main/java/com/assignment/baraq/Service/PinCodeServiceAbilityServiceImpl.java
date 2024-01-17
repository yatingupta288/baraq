package com.assignment.baraq.Service;

import com.assignment.baraq.DAO.PincodeServiceAbilityRepo;
import com.assignment.baraq.Model.PinCodeServiceAbility;
import com.assignment.baraq.ServiceImpl.PinCodeServiceAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PinCodeServiceAbilityServiceImpl implements PinCodeServiceAbilityService {

  @Autowired
  private PincodeServiceAbilityRepo pincodeServiceAbilityRepo;

  public boolean isServiceAble(String sourcePinCode, String destinationPinCode,
      String paymentMode) {
    return pincodeServiceAbilityRepo.getBySourcePinCodeAndDestinationPinCodeAndPaymentModes(
        sourcePinCode, destinationPinCode, paymentMode);
  }

  public void createPinCodeServiceAbility(String sourcePinCode, String destinationPinCode,
      String paymentMode) {
    Optional<PinCodeServiceAbility> pinCodeServiceAbility =
        isAlreadyPresent(sourcePinCode, destinationPinCode);
    if (pinCodeServiceAbility.isPresent()) {
      pinCodeServiceAbility.get().getPaymentModes().add(paymentMode);
      pincodeServiceAbilityRepo.save(pinCodeServiceAbility.get());
    } else {
      PinCodeServiceAbility pinCodeServiceAbility1 =
          PinCodeServiceAbility.builder().sourcePinCode(sourcePinCode)
              .destinationPinCode(destinationPinCode).paymentModes(addPaymentModeList(paymentMode))
              .build();

      pincodeServiceAbilityRepo.save(pinCodeServiceAbility1);
    }
  }

  @Override
  public PinCodeServiceAbility getPinCodeServiceAbility(String sourcePinCode,
      String destinationPinCode) {
    Optional<PinCodeServiceAbility> pinCodeServiceAbility =
        isAlreadyPresent(sourcePinCode, destinationPinCode);
    if (pinCodeServiceAbility.isPresent()) {
      return pinCodeServiceAbility.get();
    } else {
      throw new RuntimeException(
          "mapping does not exist for these pincodes" + sourcePinCode + " " + destinationPinCode);
    }
  }

  @Override
  public void deletePinCodeServiceAbility(String sourcePinCode,
      String destinationPinCode) {
    Optional<PinCodeServiceAbility> pinCodeServiceAbility =
        isAlreadyPresent(sourcePinCode, destinationPinCode);
    if (pinCodeServiceAbility.isPresent()) {
      pincodeServiceAbilityRepo.delete(pinCodeServiceAbility.get());
    } else {
      throw new RuntimeException(
          "mapping does not exist for these pincodes" + sourcePinCode + " " + destinationPinCode);
    }
  }

  private Optional<PinCodeServiceAbility> isAlreadyPresent(String sourcePinCode,
      String destinationPinCode) {
    return pincodeServiceAbilityRepo.findBySourcePinCodeAndDestinationPinCode(sourcePinCode,
        destinationPinCode);
  }

  private List<String> addPaymentModeList(String paymentMode) {
    List<String> paymentModes = new ArrayList<>();
    paymentModes.add(paymentMode);
    return paymentModes;
  }
}
