package com.sparta.fitpleprojectbackend.post;

import com.sparta.fitpleprojectbackend.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
