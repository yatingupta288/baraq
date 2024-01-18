package com.assignment.baraq.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Column(name = "payment_mode")
  private String paymentMode;

  @Column(name = "quantity")
  private int quantity;

  @JoinColumn(name = "product", referencedColumnName = "id", foreignKey = @ForeignKey(name =
      "FK_ORDER_PRODUCT_ID"))
  @ManyToOne
  private Product product;

  @JoinColumn(name = "Buyer", referencedColumnName = "id", foreignKey = @ForeignKey(name =
      "FK_ORDER_BUYER_ID"))
  @ManyToOne
  private Buyer buyer;
}
