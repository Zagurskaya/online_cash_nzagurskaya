package com.gmail.zagurskaya.online.cash.repository;

import com.gmail.zagurskaya.online.cash.repository.model.Reviews;

import java.sql.Connection;
import java.util.List;


public interface ReviewsRepository extends GenericRepository {

    List<Reviews> getReviews(Connection connection);

//    Reviews getReview(Connection connection, Long id);
//
//    boolean update(Connection connection, Reviews reviews);
//
//    boolean delete(Connection connection, Long id);


}
