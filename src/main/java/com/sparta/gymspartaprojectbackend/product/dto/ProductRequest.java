package com.sparta.gymspartaprojectbackend.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

  private Long trainerId;
  private Long storeId;
  private String ptTimes;
  private String paymentType;
  private double amount;
  private double productPrice;
  private String expiryDate;

}