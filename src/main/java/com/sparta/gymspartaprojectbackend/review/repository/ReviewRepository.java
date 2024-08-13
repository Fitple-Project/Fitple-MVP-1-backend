package com.sparta.gymspartaprojectbackend.review.repository;

import com.sparta.gymspartaprojectbackend.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findByUserId(Long userId);

  List<Review> findReportedReviewsByStoreId(Long storeId);
}