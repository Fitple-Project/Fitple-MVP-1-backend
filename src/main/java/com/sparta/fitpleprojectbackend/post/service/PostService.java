package com.sparta.fitpleprojectbackend.post.service;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.post.PostRepository;
import com.sparta.fitpleprojectbackend.post.dto.PostRequestDto;
import com.sparta.fitpleprojectbackend.post.entity.Post;
import com.sparta.fitpleprojectbackend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public CommonResponse<String> createPost(PostRequestDto requestDto, User user) {
    Post post = Post.builder()
      .title(requestDto.getTitle())
      .content(requestDto.getContent())
      .user(user)
      .build();

    postRepository.save(post);

    String message = "생성완료";

    return new CommonResponse<>(HttpStatus.OK.value(), "게시물 생성 완료", message);
  }
}
