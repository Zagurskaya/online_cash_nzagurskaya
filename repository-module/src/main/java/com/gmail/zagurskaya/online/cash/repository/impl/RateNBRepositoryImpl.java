package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.RateNBRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.RateNBRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.RateNB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RateNBRepositoryImpl extends GenericRepositoryImpl implements RateNBRepository {

    private static Logger logger = LoggerFactory.getLogger(RateNBRepositoryImpl.class);
//    @Override
//    public List<RateNB> getRatesNB(Connection connection) {
//        String sql = String.format(
//                "SELECT * FROM `RateNB`");
//        List<RateNB> RateNBList = new ArrayList<>();
//        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
//            ResultSet resultSet = prepared.executeQuery(sql);
//            return getRateNBFromResult(resultSet);
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//            throw new RateNBRepositoryException("Database exception during  getRateNBs ", e);
//        }
//    }
//
//    private List<RateNB> getRateNBFromResult(ResultSet resultSet) throws SQLException {
//        List<RateNB> rateNBList = new ArrayList<>();
//        while (resultSet.next()) {
//            RateNB rateNB = new RateNB();
//            rateNB.setId(resultSet.getLong("id"));
//            rateNB.setCurrencyId(resultSet.getLong("currencyId"));
//            rateNB.setDate(resultSet.getDate("date"));
//            rateNB.setSum(resultSet.getDouble("sum"));
//            rateNB.setIsNotActive(resultSet.getBoolean("isNotActive"));
//            rateNBList.add(rateNB);
//        }
//        return rateNBList;
//    }
}
