package com.sparta.fitpleprojectbackend.notification.dto;

import lombok.Getter;

@Getter
public class UserPaymentNotificationResponse {
  private String title;
  private String message;

  public UserPaymentNotificationResponse(String title, String message) {
    this.title = title;
    this.message = message;
  }
}
