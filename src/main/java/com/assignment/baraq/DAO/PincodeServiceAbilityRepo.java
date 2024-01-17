package com.assignment.baraq.DAO;

import com.assignment.baraq.Model.PinCodeServiceAbility;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Hidden
public interface PincodeServiceAbilityRepo extends JpaRepository<PinCodeServiceAbility, Long> {

  @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END AS exists "
      + "FROM pincode_serviceability p WHERE "
      + "p.source_pincode = :sourcePinCode AND p.destination_pincode = :destinationPinCode "
      + "AND :paymentMode = ANY(p.payment_modes)", nativeQuery = true)
  boolean getBySourcePinCodeAndDestinationPinCodeAndPaymentModes(
      @Param("sourcePinCode") String sourcePinCode,
      @Param("destinationPinCode") String destinationPinCode,
      @Param("paymentMode") String paymentMode);


  Optional<PinCodeServiceAbility> findBySourcePinCodeAndDestinationPinCode(String sourceCode,
      String destinationCode);

}
