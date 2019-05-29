package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.ReviewsRepository;
import com.gmail.zagurskaya.repository.model.Reviews;
import com.gmail.zagurskaya.service.ReviewsService;
import com.gmail.zagurskaya.service.converter.ReviewsConverter;
import com.gmail.zagurskaya.service.model.ReviewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewsServiceImpl implements ReviewsService {
    private static final Logger logger = LogManager.getLogger(ReviewsServiceImpl.class);

    private final ReviewsConverter reviewsConverter;
    private final ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewsConverter reviewsConverter, ReviewsRepository reviewsRepository) {
        this.reviewsConverter = reviewsConverter;
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    @Transactional
    public List<ReviewsDTO> getReviews() {
        List<Reviews> reviewss = reviewsRepository.findAll(0, Integer.MAX_VALUE);
        List<ReviewsDTO> reviewssDTO = reviewss.stream()
                .map(reviewsConverter::toDTO)
                .collect(Collectors.toList());
        return reviewssDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reviewsRepository.remove(reviewsRepository.findById(id));
    }

    @Override
    public void deleteReviewsList(List<Long> ids) {
        ids.stream().forEach(id -> {
            delete(id);
            logger.info("deleted review with id = " + id);
        });
    }
}
