package com.sparta.fitpleprojectbackend.notification.repository;

import com.sparta.fitpleprojectbackend.notification.entity.PaymentUserNotification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentUserNotificationRepository extends JpaRepository<PaymentUserNotification, Long> {
  List<PaymentUserNotification> findByUserId(Long id);
}
