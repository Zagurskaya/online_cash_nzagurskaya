package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.Reviews;
import com.gmail.zagurskaya.service.converter.ReviewsConverter;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.ReviewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewsConverterImpl implements ReviewsConverter {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Autowired
    public ReviewsConverterImpl(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewsDTO toDTO(Reviews reviews) {
        ReviewsDTO reviewsDTO = new ReviewsDTO();
        reviewsDTO.setId(reviews.getId());
        reviewsDTO.setDate(reviews.getDate());
        reviewsDTO.setUser(userConverter.toDTO(reviews.getUser()));
        reviewsDTO.setDescription(reviews.getDescription());
        return reviewsDTO;
    }

    @Override
    public Reviews toEntity(ReviewsDTO reviewsDTO) {
        Reviews reviews = new Reviews();
        reviews.setId(reviewsDTO.getId());
        reviews.setDate(reviewsDTO.getDate());
        reviews.setUser(userRepository.findById(reviewsDTO.getUser().getId()));
        reviews.setDescription(reviewsDTO.getDescription());
        return reviews;
    }
}
