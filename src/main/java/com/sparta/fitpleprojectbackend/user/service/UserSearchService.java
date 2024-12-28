package com.sparta.fitpleprojectbackend.user.service;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.user.dto.SemiUserProfile;
import com.sparta.fitpleprojectbackend.user.entity.User;
import com.sparta.fitpleprojectbackend.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSearchService {

  private final UserRepository userRepository;


  public CommonResponse<List<SemiUserProfile>> searchSemiUserProfile(String username) {

    List<User> users = userRepository.findByUserName(username);

    List<SemiUserProfile> userDto = users.stream()
      .map(user -> new SemiUserProfile(user.getId(), user.getUserName(), user.getUserPicture()))
      .toList();

    return new CommonResponse<>(HttpStatus.OK.value(), "게시물 수정 완료", userDto);
  }
}
