package com.sparta.fitpleprojectbackend.notification.repository;

import com.sparta.fitpleprojectbackend.notification.entity.UserNotification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

  List<UserNotification> findByUserId(Long user_id);
}
