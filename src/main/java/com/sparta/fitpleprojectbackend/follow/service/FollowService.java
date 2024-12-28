package com.sparta.fitpleprojectbackend.follow.service;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.enums.ErrorType;
import com.sparta.fitpleprojectbackend.exception.CustomException;
import com.sparta.fitpleprojectbackend.follow.entity.Follow;
import com.sparta.fitpleprojectbackend.follow.repository.FollowRepository;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import com.sparta.fitpleprojectbackend.user.entity.User;
import com.sparta.fitpleprojectbackend.user.repository.UserRepository;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

  private final UserRepository userRepository;
  private final FollowRepository followRepository;

  // 팔로우 요청
  public CommonResponse<String> follow(Long userId, UserDetailsImpl followUser) {

    User toUser = userRepository.findById(userId).orElse(null);
    User fromUser = followUser.getUser();

    // 자기 자신 X
    if (Objects.equals(toUser.getId(), fromUser.getId())) {
      throw new CustomException(ErrorType.NOT_FOUND_USER); // TODO: 자기 자신 팔로우 금지
    }

    // 중복 팔로우 X
    if (followRepository.findByFromUserAndToUser(fromUser, toUser).isPresent()) {
      throw new CustomException(ErrorType.NOT_FOUND_USER); // TODO: 중복 팔로우 금지
    }

    Follow follow = Follow.builder()
      .toUser(toUser)
      .fromUser(fromUser)
      .build();

    followRepository.save(follow);

    String message = "팔로우 성공";

    return new CommonResponse<>(HttpStatus.OK.value(), "팔로우 완료", message);
  }

  public CommonResponse<String> deleteFollow(Long userId, UserDetailsImpl followUser) {
    User toUser = userRepository.findById(userId).orElse(null);
    User fromUser = followUser.getUser();

    Optional<Follow> follow = followRepository.findByFromUserAndToUser(fromUser, toUser);

    followRepository.delete(follow.get());

    String message = "팔로우 삭제 성공";

    return new CommonResponse<>(HttpStatus.OK.value(), "팔로우 삭제 완료", message);
  }
}
