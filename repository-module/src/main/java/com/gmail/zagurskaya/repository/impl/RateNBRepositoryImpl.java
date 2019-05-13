package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.RateNBRepository;
import com.gmail.zagurskaya.repository.exception.RateNBRepositoryImplException;
import com.gmail.zagurskaya.repository.model.RateNB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public abstract class RateNBRepositoryImpl extends AbstractRepository implements RateNBRepository {
@Repository
public class RateNBRepositoryImpl implements RateNBRepository {

    private static Logger logger = LoggerFactory.getLogger(RateNBRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public RateNBRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

   

    @Override
    public List<RateNB> getRateNB(Connection connection) {
        return getAll(connection);
    }

    @Override
    public RateNB add(Connection connection, RateNB rateNB) {

        return create(connection, rateNB);
    }

    @Override
    public RateNB create(Connection connection, RateNB rateNB) {
        String sql = String.format("INSERT INTO `rateNB`(`currencyId`, `date`, `sum`) " +
                        "VALUES ('%s','%s','%s')",
                rateNB.getCurrencyId(), rateNB.getDate(), rateNB.getSum());

        long rateNBId = abstractRepository.executeCreate(connection, sql);
        if (rateNBId > 0) {
            rateNB.setId(rateNBId);
            return rateNB;
        } else {
            return null;
        }
    }

    @Override
    public RateNB read(Connection connection, Long id) {
        List<RateNB> rateNB = getAll(connection, " WHERE id=" + id);
        return rateNB.size() == 0 ? null : rateNB.get(0);
    }

    @Override
    public boolean update(Connection connection, RateNB rateNB) {
        String sql = String.format(
                "UPDATE `rateNB` SET `currencyId`='%s', `date`='%s', `sum`='%s' WHERE `id`='%d'",
                rateNB.getCurrencyId(), rateNB.getDate(), rateNB.getSum(), rateNB.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, RateNB rateNB) {
        String sql = String.format(
                "DELETE FROM `rateNB` WHERE `id`='%d'",
                rateNB.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public List<RateNB> getAll(Connection connection) {
        return getAll(connection, "");
    }

    @Override
    public List<RateNB> getAll(Connection connection, String where) {
        List<RateNB> rateNBList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `rateNB`" + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RateNB rateNB = new RateNB();
                rateNB.setId(resultSet.getLong("id"));
                rateNB.setCurrencyId(resultSet.getLong("currencyId"));
                rateNB.setDate(resultSet.getDate("date"));
                rateNB.setSum(resultSet.getDouble("sum"));
                rateNBList.add(rateNB);
            }
            return rateNBList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateNBRepositoryImplException("Database exception during getAll rateNB where " + where, e);
        }

    }

}
