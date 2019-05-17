package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.ReviewsDTO;

import java.util.List;

public interface ReviewsService {

    List<ReviewsDTO> getReviews();

    ReviewsDTO getReview(Long id);

    boolean update(ReviewsDTO reviews);

    boolean delete(Long id);

}
