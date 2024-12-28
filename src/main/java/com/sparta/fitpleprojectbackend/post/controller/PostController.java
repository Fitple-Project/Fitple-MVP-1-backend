package com.sparta.fitpleprojectbackend.post.controller;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.post.dto.PostRequestDto;
import com.sparta.fitpleprojectbackend.post.dto.PostResponseDto;
import com.sparta.fitpleprojectbackend.post.service.PostService;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import com.sparta.fitpleprojectbackend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

//  @GetMapping("/api/post")
//  public ResponseEntity<CommonResponse<PostResponseDto>> getAllPost() {
//    CommonResponse<String> response = postService.getAllPost();
//
//    return ResponseEntity.ok(response);
//  }

  @PutMapping("/api/post/{postId}")
  public ResponseEntity<CommonResponse<String>> updatePost(@PathVariable("postId") Long postId,@RequestBody PostRequestDto requestDto,
    @AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userDetails.getUser();
    CommonResponse<String> response = postService.updatePost(requestDto, user, postId);

    return ResponseEntity.ok(response);
  }

  @PutMapping("/api/post/{postId}/delete")
  public ResponseEntity<CommonResponse<String>> deletePost (@PathVariable("postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user = userDetails.getUser();
    CommonResponse<String> response = postService.deletePost(postId, user);

    return ResponseEntity.ok(response);
  }

}
