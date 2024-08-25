package com.sparta.fitpleprojectbackend.notification.entity;

import com.sparta.fitpleprojectbackend.common.TimeStamped;
import com.sparta.fitpleprojectbackend.owner.entity.Owner;
import com.sparta.fitpleprojectbackend.payment.entity.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PaymentOwnerNotification extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;

  private String message;

  // 결제와 연결 필요
  @OneToOne
  @JoinColumn(name = "payment_id")
  private Payment payment;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Owner owner;

  public PaymentOwnerNotification(String title, String message, Payment payment) {
    this.title = title;
    this.message = message;
    this.payment = payment;
    this.owner = payment.getStore().getOwner();
  }
}
