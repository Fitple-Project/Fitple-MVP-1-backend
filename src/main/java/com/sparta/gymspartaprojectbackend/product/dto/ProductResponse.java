package com.sparta.gymspartaprojectbackend.product.dto;

import com.sparta.gymspartaprojectbackend.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
  private Long productId;
  private String trainerName;
  private String ptTimes;
  private double amount;
  private double productPrice;

  public ProductResponse(Product product) {
    this.productId = product.getId();
    this.trainerName = product.getTrainer() != null ? product.getTrainer().getTrainerName() : null;
    this.ptTimes = product.getPtTimes() != null ? product.getPtTimes().toString() : null;
    this.amount = product.getAmount();
    this.productPrice = product.getProductPrice();
  }
}