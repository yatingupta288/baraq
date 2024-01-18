package com.assignment.baraq.Response;

import com.assignment.baraq.Model.Buyer;
import com.assignment.baraq.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse implements Serializable {

  private Long id;

  private String paymentMode;

  private int quantity;

  private Product product;

  private Buyer buyer;

}
