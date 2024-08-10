package com.sparta.fltpleprojectbackend.user.dto;

import com.sparta.fltpleprojectbackend.user.entity.User;
import lombok.Getter;

@Getter
public class ReadUserResponse {
  private String username;

  private String accountId;

  private String nickname;

  private String email;

  private String userPicture;

  private String zipcode;

  private String mainAddress;

  private String detailedAddress;

  private String phoneNumber;

  public ReadUserResponse(User user) {
    this.username = user.getUserName();
    this.accountId = user.getAccountId();
    this.nickname = user.getNickname();
    this.email = user.getEmail();
    this.userPicture = user.getUserPicture();
    this.zipcode = user.getZipcode();
    this.mainAddress = user.getMainAddress();
    this.detailedAddress = user.getDetailedAddress();
    this.phoneNumber = user.getPhoneNumber();
  }
}
