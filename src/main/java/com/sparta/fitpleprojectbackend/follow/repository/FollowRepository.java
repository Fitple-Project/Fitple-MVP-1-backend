package com.sparta.fitpleprojectbackend.follow.repository;

import com.sparta.fitpleprojectbackend.follow.entity.Follow;
import com.sparta.fitpleprojectbackend.user.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

  // 팔로우 관계를 찾는 메서드 (fromUser가 toUser를 팔로우하는 관계)
  Optional<Follow> findByFromUserAndToUser(@Param("from") User fromUser, @Param("to") User toUser);

}
