package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.ReviewsDTO;

import java.util.List;

public interface ReviewsService {

    List<ReviewsDTO> getReviews();

    ReviewsDTO getReview(Long id);

    void update(ReviewsDTO reviews);

    void delete(Long id);

}
