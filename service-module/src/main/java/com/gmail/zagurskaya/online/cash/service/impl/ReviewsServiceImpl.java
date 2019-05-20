package com.gmail.zagurskaya.online.cash.service.impl;

import com.gmail.zagurskaya.online.cash.repository.ReviewsRepository;
import com.gmail.zagurskaya.online.cash.repository.model.Reviews;
import com.gmail.zagurskaya.online.cash.service.ReviewsService;
import com.gmail.zagurskaya.online.cash.service.converter.ReviewsConverter;
import com.gmail.zagurskaya.online.cash.service.exception.ReviewsServiceException;
import com.gmail.zagurskaya.online.cash.service.model.ReviewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final ReviewsConverter reviewsConverter;
    private final ReviewsRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewsConverter reviewsConverter, ReviewsRepository reviewsRepository) {
        this.reviewsConverter = reviewsConverter;
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public List<ReviewsDTO> getReviews() {
        try (Connection connection = reviewsRepository.getConnection()) {
            return getReportsEndDayWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceException("Exception in getReportsEndDay", e);
        }
    }

    private List<ReviewsDTO> getReportsEndDayWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<Reviews> reviewss = reviewsRepository.getReviews(connection);
            List<ReviewsDTO> reviewssDTO = reviewss.stream()
                    .map(reviewsConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return reviewssDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceException("Exception in getAll witch reviewss", e);
        }
    }

    @Override
    public void update(ReviewsDTO reviewsDTO) {
        try (Connection connection = reviewsRepository.getConnection()) {
                    updateWitchConnection(connection, reviewsDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceException("Exception in add reviewss", e);
        }
    }


    private void updateWitchConnection(Connection connection, ReviewsDTO reviewsDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Reviews reviews = reviewsConverter.toEntity(reviewsDTO);
                    reviewsRepository.merge(reviews);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceException("Exception in add reviewss witch Connection", e);
        }
    }

    @Override
    public ReviewsDTO getReview(Long id) {
        try (Connection connection = reviewsRepository.getConnection()) {
            return getReviewsWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceException("Exception in get Reviews Witch Connection ", e);
        }
    }

    private ReviewsDTO getReviewsWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Reviews reviews = (Reviews) reviewsRepository.findById(id);
            ReviewsDTO reviewsDTO = reviewsConverter.toDTO(reviews);
            connection.commit();
            return reviewsDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceException("Exception in read reviews witch ID", e);
        }
    }
}
