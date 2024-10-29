package com.sparta.fitpleprojectbackend.post.controller;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.post.dto.PostRequestDto;
import com.sparta.fitpleprojectbackend.post.service.PostService;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import com.sparta.fitpleprojectbackend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/api/post")
  public ResponseEntity<CommonResponse<String>> createPost(@RequestBody PostRequestDto requestDto,
    @AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userDetails.getUser();
    CommonResponse<String> response = postService.createPost(requestDto, user);

    return ResponseEntity.ok(response);
  }
}
