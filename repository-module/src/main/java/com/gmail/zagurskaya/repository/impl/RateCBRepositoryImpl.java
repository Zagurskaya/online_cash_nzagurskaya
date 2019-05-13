package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.RateCBRepository;
import com.gmail.zagurskaya.repository.exception.RateCBRepositoryImplException;
import com.gmail.zagurskaya.repository.model.RateCB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public abstract class RateCBRepositoryImpl extends AbstractRepository implements RateCBRepository {
@Repository
public class RateCBRepositoryImpl implements RateCBRepository {

    private static Logger logger = LoggerFactory.getLogger(RateCBRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public RateCBRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

   

    @Override
    public List<RateCB> getRateCB(Connection connection) {
        return getAll(connection);
    }

    @Override
    public RateCB add(Connection connection, RateCB rateCB) {

        return create(connection, rateCB);
    }

    @Override
    public RateCB create(Connection connection, RateCB rateCB) {
        String sql = String.format("INSERT INTO `rateCB`(`coming`, `spending`, `timestamp`, `sum`, `isBack`) " +
                        "VALUES ('%s','%s','%s','%s','%s')",
                rateCB.getComing(), rateCB.getSpending(), rateCB.getTimestamp(), rateCB.getSum(), rateCB.getIsBack()? 1 : 0);

        long rateCBId = abstractRepository.executeCreate(connection, sql);
        if (rateCBId > 0) {
            rateCB.setId(rateCBId);
            return rateCB;
        } else {
            return null;
        }
    }

    @Override
    public RateCB read(Connection connection, Long id) {
        List<RateCB> rateCB = getAll(connection, " WHERE id=" + id);
        return rateCB.size() == 0 ? null : rateCB.get(0);
    }

    @Override
    public boolean update(Connection connection, RateCB rateCB) {
        String sql = String.format(
                "UPDATE `rateCB` SET `coming`='%s', `spending`='%s', `timestamp`='%s', `sum`='%s', `isBack`='%s' " +
                        "WHERE `id`='%d'",
                rateCB.getComing(), rateCB.getSpending(), rateCB.getTimestamp(), rateCB.getSum(), rateCB.getIsBack(),
                rateCB.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, RateCB rateCB) {
        String sql = String.format(
                "DELETE FROM `rateCB` WHERE `id`='%d'",
                rateCB.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public List<RateCB> getAll(Connection connection) {
        return getAll(connection, "");
    }

    @Override
    public List<RateCB> getAll(Connection connection, String where) {
        List<RateCB> rateCBList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `rateCB`" + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RateCB rateCB = new RateCB();
                rateCB.setId(resultSet.getLong("id"));
                rateCB.setComing(resultSet.getLong("coming"));
                rateCB.setSpending(resultSet.getLong("spending"));
                rateCB.setTimestamp(resultSet.getTimestamp("timestamp"));
                rateCB.setSum(resultSet.getDouble("sum"));
                rateCB.setIsBack(resultSet.getBoolean("isBack"));
                rateCBList.add(rateCB);
            }
            return rateCBList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RateCBRepositoryImplException("Database exception during getAll rateCB where " + where, e);
        }

    }

}
