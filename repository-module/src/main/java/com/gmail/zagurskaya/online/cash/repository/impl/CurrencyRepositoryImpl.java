package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.CurrencyRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.CurrencyRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyRepositoryImpl extends GenericRepositoryImpl implements CurrencyRepository {

    private static Logger logger = LoggerFactory.getLogger(CurrencyRepositoryImpl.class);

//    @Override
//    public List<Currency> getCurrencies(Connection connection) {
//        String sql = String.format(
//                "SELECT * FROM `currencies`");
//        List<Currency> currencyList = new ArrayList<>();
//        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
//            ResultSet resultSet = prepared.executeQuery(sql);
//            return getCurrenciesFromResult(resultSet);
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//            throw new CurrencyRepositoryException("Database exception during  getCurrencies ", e);
//        }
//    }
//
//    private List<Currency> getCurrenciesFromResult(ResultSet resultSet) throws SQLException {
//        List<Currency> currencyList = new ArrayList<>();
//        while (resultSet.next()) {
//            Currency currency = new Currency();
//            currency.setId(resultSet.getLong("id"));
//            currency.setIso(resultSet.getString("iso"));
//            currency.setName(resultSet.getString("name"));
//            currencyList.add(currency);
//        }
//        return currencyList;
//    }

}
