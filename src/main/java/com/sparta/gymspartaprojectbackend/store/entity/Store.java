package com.sparta.gymspartaprojectbackend.store.entity;

import com.sparta.gymspartaprojectbackend.common.TimeStamped;
import com.sparta.gymspartaprojectbackend.owner.entity.Owner;
import com.sparta.gymspartaprojectbackend.product.entity.Product;
import com.sparta.gymspartaprojectbackend.store.dto.StoreRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Store extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String storeName;

  @Column(nullable = false)
  private String address;

//  @Column(nullable = false)
//  private String streetAddress;
//
//  @Column(nullable = false)
//  private String postalCode;

  @Column(nullable = false)
  private String storeInfo;

  @Column(nullable = false)
  private String storeHour;

  @Column(nullable = false)
  private String storeTel;

  // 추가 필드
  @Column
  private String price;

  @Column(nullable = true)
  private String image;

  @ElementCollection
  private List<String> reviews;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", nullable = false)
  private Owner owner;

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> prouducts;

  public Store(String storeName, String address, String storeInfo, String storeHour, String storeTel,
    String price, String image, Owner owner) {
    this.storeName = storeName;
    this.address = address;
    this.storeInfo = storeInfo;
    this.storeHour = storeHour;
    this.storeTel = storeTel;
    this.price = price;
    this.image = image;
    this.owner = owner;
  }

  public Store(StoreRequest request, Owner owner) {
    this.owner = owner;
    this.storeName = request.getStoreName();
    this.address = request.getAddress();
//    this.streetAddress = request.getStreetAddress();
//    this.postalCode = request.getPostalCode();
    this.storeInfo = request.getStoreInfo();
    this.storeHour = request.getStoreHour();
    this.storeTel = request.getStoreTel();
    this.price = request.getPrice(); // 추가 필드
    this.image = request.getImage(); // 추가 필드
    this.reviews = request.getReviews(); // 추가 필드
  }

  public void update(StoreRequest request) {
    this.storeName = request.getStoreName();
    this.address = request.getAddress();
//    this.streetAddress = request.getStreetAddress();
//    this.postalCode = request.getPostalCode();
    this.storeInfo = request.getStoreInfo();
    this.storeHour = request.getStoreHour();
    this.storeTel = request.getStoreTel();
    this.price = request.getPrice(); // 추가 필드
    this.image = request.getImage(); // 추가 필드
    this.reviews = request.getReviews(); // 추가 필드
  }
}
