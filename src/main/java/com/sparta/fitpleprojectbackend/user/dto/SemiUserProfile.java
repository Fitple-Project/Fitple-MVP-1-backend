package com.sparta.fitpleprojectbackend.user.dto;

import lombok.Getter;

@Getter
public class SemiUserProfile {
  private final Long userId;
  private final String userName;
  private final String userPicture;

  public SemiUserProfile(Long userId, String userName, String userPicture) {
    this.userId = userId;
    this.userName = userName;
    this.userPicture = userPicture;
  }
}
