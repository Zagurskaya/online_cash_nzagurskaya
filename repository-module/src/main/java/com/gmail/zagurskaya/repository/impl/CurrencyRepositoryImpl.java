package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.CurrencyRepository;
import com.gmail.zagurskaya.repository.exception.CurrencyRepositoryImplException;
import com.gmail.zagurskaya.repository.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public abstract class CurrencyRepositoryImpl extends AbstractRepository implements CurrencyRepository {
@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {

    private static Logger logger = LoggerFactory.getLogger(CurrencyRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public CurrencyRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }



    @Override
    public List<Currency> getCurrency(Connection connection) {
        return getAll(connection);
    }

    @Override
    public Currency add(Connection connection, Currency currency) {

        return create(connection, currency);
    }

    @Override
    public Currency create(Connection connection, Currency currency) {
        String sql = String.format("INSERT INTO `currencies`(`iso`, `name`) " +
                        "VALUES ('%s','%s')",
                currency.getIso(), currency.getName());

        long currencyId = abstractRepository.executeCreate(connection, sql);
        if (currencyId > 0) {
            currency.setId(currencyId);
            return currency;
        } else {
            return null;
        }
    }

    @Override
    public Currency read(Connection connection, Long id) {
        List<Currency> currencies = getAll(connection, " WHERE id=" + id);
        return currencies.size() == 0 ? null : currencies.get(0);
    }

    @Override
    public boolean update(Connection connection, Currency currency) {
        String sql = String.format(
                "UPDATE `currencies` SET `iso`='%s', `name`='%s' WHERE `id`='%d'",
                currency.getIso(), currency.getName(), currency.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, Currency currency) {
        String sql = String.format(
                "DELETE FROM `currencies` WHERE `id`='%d'",
                currency.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public List<Currency> getAll(Connection connection) {
        return getAll(connection, "");
    }

    @Override
    public List<Currency> getAll(Connection connection, String where) {
        List<Currency> currencyList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `currencies`" + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Currency currency = new Currency();
                currency.setId(resultSet.getLong("id"));
                currency.setIso(resultSet.getString("iso"));
                currency.setName(resultSet.getString("name"));
                currencyList.add(currency);
            }
            return currencyList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new CurrencyRepositoryImplException("Database exception during getAll currency where " + where, e);
        }

    }

}
