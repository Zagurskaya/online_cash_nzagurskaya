package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.Reviews;
import com.gmail.zagurskaya.online.cash.service.converter.ReviewsConverter;
import com.gmail.zagurskaya.online.cash.service.model.ReviewsDTO;
import org.springframework.stereotype.Component;

@Component
public class ReviewsConverterImpl implements ReviewsConverter {
    @Override
    public ReviewsDTO toDTO(Reviews reviews) {
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        reviewsDTO.setId(reviews.getId());
        reviewsDTO.setDate(reviews.getDate());
        reviewsDTO.setUserId(reviews.getUserId());
        reviewsDTO.setDescription(reviews.getDescription());
        reviewsDTO.setIsOpen(reviews.getIsOpen());
        return reviewsDTO;
    }

    @Override
    public Reviews toEntity(ReviewsDTO reviewsDTO) {
        Reviews reviews = new Reviews();
        reviews.setId(reviewsDTO.getId());
        reviews.setDate(reviewsDTO.getDate());
        reviews.setUserId(reviewsDTO.getUserId());
        reviews.setDescription(reviewsDTO.getDescription());
        reviews.setIsOpen(reviewsDTO.getIsOpen());
        return reviews;
    }
}
