package com.sparta.fitpleprojectbackend.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoPayRefundRequest {
  private int cancelAmount;
  private int cancelTaxFreeAmount;
}