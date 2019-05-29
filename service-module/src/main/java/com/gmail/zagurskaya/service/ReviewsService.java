package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.ReviewsDTO;

import java.util.List;

public interface ReviewsService {

    List<ReviewsDTO> getReviews();

    void delete(Long id);

    void deleteReviewsList(List<Long> ids);
}
