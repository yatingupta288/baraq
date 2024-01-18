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
public class ProductResponse implements Serializable {

  private Long id;

  private String productName;

  private int inventory;

  private int price;

  private String pickUpAddress;
}
