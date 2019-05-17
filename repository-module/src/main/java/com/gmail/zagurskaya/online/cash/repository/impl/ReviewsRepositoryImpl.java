package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.ReviewsRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.ReviewsRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.exception.RoleRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.Reviews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewsRepositoryImpl implements ReviewsRepository {

    private static Logger logger = LoggerFactory.getLogger(ReviewsRepositoryImpl.class);

    @Override
    public List<Reviews> getReviews(Connection connection) {
        String sql = String.format(
                "SELECT * FROM `reviews` WHERE`isNotOpen`= 0");
        List<Reviews> reviewList = new ArrayList<>();
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            ResultSet resultSet = prepared.executeQuery(sql);
            return getReviewsFromResult(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsRepositoryException("Database exception during  getReviews ", e);
        }
    }

    public Reviews create(Connection connection, Reviews reviews) {
        String sql = "INSERT INTO `reviews`( date , reviewId, description, isNotOpen) " +
                "VALUES (?,?,?,?)";
        List<Reviews> reviewList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, reviews.getDate());
            preparedStatement.setLong(2, reviews.getUserId());
            preparedStatement.setString(3, reviews.getDescription());
            preparedStatement.setLong(7, reviews.getIsNotOpen() ? 1 : 0);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                reviews.setId(resultSet.getLong(1));
            }
            return reviews;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsRepositoryException("Database exception during add review ", e);
        }
    }

    @Override
    public Reviews getReview(Connection connection, Long id) {
        String sql = "SELECT * FROM reviews  WHERE id = ? ";
        List<Reviews> reviewList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getReviewsFromResult(resultSet).get(0);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsRepositoryException("Database exception during  getReview ", e);
        }
    }

    @Override
    public boolean update(Connection connection, Reviews reviews) {
        String sql = "UPDATE reviews SET date = ?, reviewId = ?, description = ?, isOpen = ? WHERE  id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, reviews.getDate());
            preparedStatement.setLong(2, reviews.getId());
            preparedStatement.setString(3, reviews.getDescription());
            preparedStatement.setLong(4, reviews.getIsNotOpen() ? 1 : 0);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return 1 == result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsRepositoryException("Database exception during  update reviews ", e);
        }
    }

    @Override
    public boolean delete(Connection connection, Long id) {
        String sql = "UPDATE reviews SET isNotOpen = 1 WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return 1 == result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ReviewsRepositoryException("Database exception during  update reviews ", e);
        }
    }

    private List<Reviews> getReviewsFromResult(ResultSet resultSet) throws SQLException {
        List<Reviews> reviewList = new ArrayList<>();
        while (resultSet.next()) {
            Reviews reviews = new Reviews();
            reviews.setId(resultSet.getLong("id"));
            reviews.setDate(resultSet.getDate("date"));
            reviews.setUserId(resultSet.getLong("userId"));
            reviews.setDescription(resultSet.getString("description"));
            reviews.setIsNotOpen(resultSet.getBoolean("isNotOpen"));
            reviewList.add(reviews);
        }
        return reviewList;
    }
}
