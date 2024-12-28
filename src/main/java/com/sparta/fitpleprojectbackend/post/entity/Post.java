package com.sparta.fitpleprojectbackend.post.entity;

import com.sparta.fitpleprojectbackend.common.TimeStamped;
import com.sparta.fitpleprojectbackend.enums.postStatus;
import com.sparta.fitpleprojectbackend.post.dto.PostRequestDto;
import com.sparta.fitpleprojectbackend.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String postTitle;

  private String postContent;

  private String postPicture;

  private LocalDateTime deletedAt;

  private postStatus poststatus;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Builder
  public Post(PostRequestDto requestDto ,User user) {
    this.postTitle = requestDto.getTitle();
    this.postContent = requestDto.getContent();
    this.postPicture = requestDto.getPicture();
    this.user = user;
    this.poststatus = postStatus.NORMAL;
  }

  public void updatePost(String title, String content) {
    this.postTitle = title;
    this.postContent = content;
  }

  public void deletePost() {
    poststatus = postStatus.DELETE;
    deletedAt = LocalDateTime.now();
  }
}
