package com.sparta.gymspartaprojectbackend.product.entity;

import com.sparta.gymspartaprojectbackend.common.TimeStamped;
import com.sparta.gymspartaprojectbackend.payment.enums.PtTimes;
import com.sparta.gymspartaprojectbackend.store.entity.Store;
import com.sparta.gymspartaprojectbackend.trainer.entity.Trainer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Product extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = true)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trainer_id", nullable = true)
  private Trainer trainer;

  @Enumerated(EnumType.STRING)
  @Column(nullable = true)
  private PtTimes ptTimes;

  @Column(nullable = false)
  private double amount;

  @Getter
  @Column(nullable = false)
  private double productPrice;

  @Column(nullable = false)
  private boolean isActive;

  protected Product() {
  }

  public Product(Trainer trainer, PtTimes ptTimes,
      double amount, double productPrice, boolean isActive) {
    this.trainer = trainer;
    this.ptTimes = ptTimes;
    this.amount = amount;
    this.productPrice = productPrice;
    this.isActive = isActive;
  }

  public Product(Store store, double amount, double productPrice,
      boolean isActive) {
    this.store = store;
    this.amount = amount;
    this.productPrice = productPrice;
    this.isActive = isActive;
  }

  public void deactivate() {
    this.isActive = false;
  }

}