package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.ReviewsRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.RoleRepositoryImplException;
import com.gmail.zagurskaya.online.cash.repository.model.Reviews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewsRepositoryImpl implements ReviewsRepository {

    private static Logger logger = LoggerFactory.getLogger(ReviewsRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public ReviewsRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }


    @Override
    public List<Reviews> getReviews(Connection connection) {
        return getAll(connection);
    }

    public Reviews create(Connection connection, Reviews reviews) {
        String sql = String.format("INSERT INTO `reviews`(`date`, `userId`, `description`, `isOpen`) " +
                        "VALUES ('%s','%s','%s','%s')",
                reviews.getDate(), reviews.getUserId(), reviews.getDescription(), reviews.getIsOpen() ? 1 : 0);

        long reviewsId = abstractRepository.executeCreate(connection, sql);
        if (reviewsId > 0) {
            reviews.setId(reviewsId);
            return reviews;
        } else {
            return null;
        }
    }

    @Override
    public Reviews getReview(Connection connection, Long id) {
        List<Reviews> reviewsList = getAll(connection, " WHERE id=" + id);
        return reviewsList.size() == 0 ? null : reviewsList.get(0);
    }

    @Override
    public boolean update(Connection connection, Reviews reviews) {
        String sql = String.format(
                "UPDATE `reviews` SET `date`='%s', `userId`='%s', `description`='%s', `isOpen`='%s'" +
                        " WHERE `id`='%d'",
                reviews.getDate(), reviews.getUserId(), reviews.getDescription(), reviews.getIsOpen() ? 1 : 0,
                reviews.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, Long id) {
        String sql = String.format(
                "DELETE FROM `reviews` WHERE `id`='%d'",
                id);
        return abstractRepository.executeUpdate(connection, sql);
    }

    public List<Reviews> getAll(Connection connection) {
        return getAll(connection, "");
    }

    public List<Reviews> getAll(Connection connection, String where) {
        List<Reviews> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `reviews` " + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Reviews Reviews = new Reviews();
                Reviews.setId(resultSet.getLong("id"));
                Reviews.setDate(resultSet.getDate("date"));
                Reviews.setUserId(resultSet.getLong("userId"));
                Reviews.setDescription(resultSet.getString("description"));
                Reviews.setIsOpen(resultSet.getBoolean("isOpen"));
                result.add(Reviews);
            }
            return result;
        } catch (SQLException e) {
            throw new RoleRepositoryImplException("Database exception during getALL Role where" + where, e);
        }
    }
}
