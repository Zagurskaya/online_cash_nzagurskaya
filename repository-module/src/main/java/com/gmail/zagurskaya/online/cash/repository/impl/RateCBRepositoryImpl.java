package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.RateCBRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.RateCBRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.RateCB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RateCBRepositoryImpl extends GenericRepositoryImpl implements RateCBRepository {

    private static Logger logger = LoggerFactory.getLogger(RateCBRepositoryImpl.class);

//    @Override
//    public List<RateCB> getRatesCB(Connection connection) {
//        String sql = String.format(
//                "SELECT * FROM `rateCB`");
//        List<RateCB> rateCBList = new ArrayList<>();
//        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
//            ResultSet resultSet = prepared.executeQuery(sql);
//            return getRateCBFromResult(resultSet);
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//            throw new RateCBRepositoryException("Database exception during  getRateCBs ", e);
//        }
//    }
//
//    private List<RateCB> getRateCBFromResult(ResultSet resultSet) throws SQLException {
//        List<RateCB> rateCBList = new ArrayList<>();
//        while (resultSet.next()) {
//            RateCB rateCB = new RateCB();
//            rateCB.setId(resultSet.getLong("id"));
//            rateCB.setComing(resultSet.getLong("coming"));
//            rateCB.setSpending(resultSet.getLong("spending"));
//            rateCB.setTimestamp(resultSet.getTimestamp("timestamp"));
//            rateCB.setSum(resultSet.getDouble("sum"));
//            rateCB.setIsBack(resultSet.getBoolean("isBack"));
//            rateCB.setIsNotActive(resultSet.getBoolean("isNotActive"));
//            rateCBList.add(rateCB);
//        }
//        return rateCBList;
//    }

}
