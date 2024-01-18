package com.assignment.baraq.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PinCodeServiceAbilityResponse implements Serializable {

  private Long id;

  private String sourcePinCode;

  private String destinationPinCode;
}
