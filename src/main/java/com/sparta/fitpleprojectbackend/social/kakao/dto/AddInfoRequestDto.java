package com.sparta.fitpleprojectbackend.social.kakao.dto;

import lombok.Getter;

@Getter
public class AddInfoRequestDto {
  // 이곳에 입력 조건 걸면 아마 될듯
  // 이름
  // 휴대폰 번호
  // 생년월일
  private String name;
  private String phoneNumber;
  private Long birthday;
}
