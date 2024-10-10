package com.sparta.gymspartaprojectbackend.store.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequest {
  private String storeName;
  private String address;
  //  private String streetAddress;
//  private String postalCode;
  private String storeInfo;
  private String storeHour;
  private String storeTel;
  private String price; // 추가 필드
  private String image; // 추가 필드
  private List<String> reviews; // 추가 필드
}
