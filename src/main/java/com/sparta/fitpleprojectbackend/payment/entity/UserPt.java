package com.sparta.fitpleprojectbackend.payment.entity;

import com.sparta.fitpleprojectbackend.common.TimeStamped;
import com.sparta.fitpleprojectbackend.payment.enums.PaymentStatus;
import com.sparta.fitpleprojectbackend.payment.enums.PaymentType;
import com.sparta.fitpleprojectbackend.payment.enums.PtTimes;
import com.sparta.fitpleprojectbackend.trainer.entity.Trainer;
import com.sparta.fitpleprojectbackend.user.entity.User;
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
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Entity
public class UserPt extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trainer_id", nullable = false)
  private Trainer trainer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PtTimes ptTimes;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentType paymentType;

  @Column
  private double amount;

  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus;

  @Column
  private LocalDateTime paymentDate;

  @Column
  private LocalDateTime expiryDate;

  @Column
  private boolean isMembership;

  @Column(nullable = false)
  private boolean isActive;

  protected UserPt() {
  }

  public UserPt(Trainer trainer, User user, PtTimes ptTimes, PaymentType paymentType, double amount,
      PaymentStatus paymentStatus, LocalDateTime paymentDate, LocalDateTime expiryDate,
      boolean isMembership, boolean isActive) {
    this.trainer = trainer;
    this.user = user;
    this.ptTimes = ptTimes;
    this.paymentType = paymentType;
    this.amount = amount;
    this.paymentStatus = paymentStatus;
    this.paymentDate = paymentDate;
    this.expiryDate = expiryDate;
    this.isMembership = isMembership;
    this.isActive = isActive;
  }
}