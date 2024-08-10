package com.sparta.fltpleprojectbackend.review.dto;

import com.sparta.fltpleprojectbackend.review.entity.StoreReview;
import com.sparta.fltpleprojectbackend.review.entity.TrainerReview;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private int rating;
    private String comment;

    public ReviewResponse(StoreReview storeReview) {
        this.id = storeReview.getId();
        this.rating = storeReview.getRating();
        this.comment = storeReview.getComment();
    }

    public ReviewResponse(TrainerReview trainerReview) {
        this.id = trainerReview.getId();
        this.rating = trainerReview.getRating();
        this.comment = trainerReview.getComment();
    }
}
