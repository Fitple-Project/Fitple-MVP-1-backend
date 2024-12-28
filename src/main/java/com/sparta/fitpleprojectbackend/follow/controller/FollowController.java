package com.sparta.fitpleprojectbackend.follow.controller;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.follow.service.FollowService;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  @PostMapping("/api/follow/{userId}")
  public ResponseEntity<CommonResponse<String>> follow(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl followUser) {

    CommonResponse<String> response = followService.follow(userId, followUser);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/api/follow/{userId}")
  public ResponseEntity<CommonResponse<String>> deleteFollow(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl followUser) {

    CommonResponse<String> response = followService.deleteFollow(userId, followUser);

    return ResponseEntity.ok(response);
  }
}
