package com.sparta.fitpleprojectbackend.post.service;

import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.enums.ErrorType;
import com.sparta.fitpleprojectbackend.exception.CustomException;
import com.sparta.fitpleprojectbackend.post.PostRepository;
import com.sparta.fitpleprojectbackend.post.dto.PostRequestDto;
import com.sparta.fitpleprojectbackend.post.entity.Post;
import com.sparta.fitpleprojectbackend.user.entity.User;
import java.util.Objects;
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
      .requestDto(requestDto)
      .user(user)
      .build();

    postRepository.save(post);

    String message = "생성완료";

    return new CommonResponse<>(HttpStatus.OK.value(), "게시물 생성 완료", message);
  }

  @Transactional
  public CommonResponse<String> updatePost(PostRequestDto requestDto, User user, Long postId) {

    Post post = postRepository.findById(postId).orElseThrow (()-> new CustomException(ErrorType.NOT_FOUND_POST));

    // 수정하는 사람이 게시물에 주인인지 확인
    if(!Objects.equals(user.getId(), post.getUser().getId())) {
      throw new CustomException(ErrorType.NOT_FOUND_USER);
    }

    post.updatePost(requestDto.getTitle(), requestDto.getContent());

    postRepository.save(post);

    String message = "수정완료";

    return new CommonResponse<>(HttpStatus.OK.value(), "게시물 수정 완료", message);
  }

  @Transactional
  public CommonResponse<String> deletePost(Long postId, User user) {
    Post post = postRepository.findById(postId).orElseThrow (()-> new CustomException(ErrorType.NOT_FOUND_POST));
    if(!Objects.equals(user.getId(), post.getUser().getId())) {
      throw new CustomException(ErrorType.NOT_FOUND_USER);
    }

    post.deletePost();

    postRepository.save(post);

    String message = "삭제완료";

    return new CommonResponse<>(HttpStatus.OK.value(), "게시물 삭제 완료", message);
  }

//  public CommonResponse<String> getAllPost() {
//      postRepository.findAll().forEach(post -> {});
//  }
}
