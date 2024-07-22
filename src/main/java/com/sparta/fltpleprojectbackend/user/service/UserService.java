package com.sparta.fltpleprojectbackend.user.service;

import com.sparta.fltpleprojectbackend.user.dto.UserSignupRequest;
import com.sparta.fltpleprojectbackend.user.entity.User;
import com.sparta.fltpleprojectbackend.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * 유저 회원가입 처리
   * @param request 회원가입 요청 정보 (아이디, 비밀번호, 이메일, 전화번호, 이름)
   * @return User 생성된 유저 객체
   * @throws IllegalArgumentException 비밀번호 불일치 또는 이미 존재하는 사용자
   */
  public User signup(UserSignupRequest request) {
    if (!request.getPassword().equals(request.getConfirmPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
      throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
    });

    userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
    });

    userRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(user -> {
      throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
    });

    User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .phoneNumber(request.getPhoneNumber())
        .name(request.getName())
        .role("USER")
        .createdAt(LocalDateTime.now())
        .build();

    return userRepository.save(user);
  }
}