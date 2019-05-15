package com.gmail.zagurskaya.online.cash.service.impl;

import com.gmail.zagurskaya.online.cash.repository.ReviewsRepository;
import com.gmail.zagurskaya.online.cash.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.online.cash.repository.model.Reviews;
import com.gmail.zagurskaya.online.cash.service.ReviewsService;
import com.gmail.zagurskaya.online.cash.service.converter.ReviewsConverter;
import com.gmail.zagurskaya.online.cash.service.exception.ReviewsServiceImplException;
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
    private final ConnectionHandler connectionHandler;

    public ReviewsServiceImpl(ReviewsConverter reviewsConverter, ReviewsRepository reviewsRepository, ConnectionHandler connectionHandler) {
        this.reviewsConverter = reviewsConverter;
        this.reviewsRepository = reviewsRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<ReviewsDTO> getReviews() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getReportsEndDayWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in getReportsEndDay", e);
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
            throw new ReviewsServiceImplException("Exception in getAll witch reviewss", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionHandler.getConnection()) {
            return deleteWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in delete reviews", e);
        }
    }

    private boolean deleteWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            boolean deleted = reviewsRepository.delete(connection, id);
            connection.commit();
            return deleted;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in delete reviews witch Connection", e);
        }
    }

    @Override
    public boolean update(ReviewsDTO reviewsDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            return updateWitchConnection(connection, reviewsDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in add reviewss", e);
        }
    }


    private boolean updateWitchConnection(Connection connection, ReviewsDTO reviewsDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Reviews reviews = reviewsConverter.toEntity(reviewsDTO);
            boolean update = reviewsRepository.update(connection, reviews);
            connection.commit();
            return update;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in add reviewss witch Connection", e);
        }
    }

    @Override
    public ReviewsDTO getReview(Long id) {
        try (Connection connection = connectionHandler.getConnection()) {
            return getReviewsWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in get Reviews Witch Connection ", e);
        }
    }

    private ReviewsDTO getReviewsWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Reviews reviews = reviewsRepository.getReview(connection, id);
            ReviewsDTO reviewsDTO = reviewsConverter.toDTO(reviews);
            connection.commit();
            return reviewsDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ReviewsServiceImplException("Exception in read reviews witch ID", e);
        }
    }
}
