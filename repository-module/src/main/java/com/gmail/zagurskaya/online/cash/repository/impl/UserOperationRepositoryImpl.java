package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.UserOperationRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.UserOperationException;
import com.gmail.zagurskaya.online.cash.repository.model.UserOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserOperationRepositoryImpl extends GenericRepositoryImpl implements UserOperationRepository {

    private static Logger logger = LoggerFactory.getLogger(UserOperationRepositoryImpl.class);

    @Override
    public List<UserOperation> getUserOperations(Connection connection) {
        String sql = String.format(
                "SELECT * FROM `usersOperations`");
        List<UserOperation> userOperationList = new ArrayList<>();
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            ResultSet resultSet = prepared.executeQuery(sql);
            return getUserOperationFromResult(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserOperationException("Database exception during  getUserOperation ", e);
        }
    }

    private List<UserOperation> getUserOperationFromResult(ResultSet resultSet) throws SQLException {
        List<UserOperation> userOperationList = new ArrayList<>();
        while (resultSet.next()) {
            UserOperation userOperation = new UserOperation();
            userOperation.setId(resultSet.getLong("id"));
            userOperation.setTimestamp(resultSet.getTimestamp("timestamp"));
            userOperation.setRate(resultSet.getDouble("rate"));
            userOperation.setSum(resultSet.getDouble("sum"));
            userOperation.setCurrencyId(resultSet.getLong("currencyId"));
            userOperation.setUserId(resultSet.getLong("userId"));
            userOperation.setOperationId(resultSet.getLong("operationId"));
            userOperation.setSpecification(resultSet.getString("specification"));
            userOperation.setSpecification(resultSet.getString("fio"));
            userOperationList.add(userOperation);
        }
        return userOperationList;
    }

}
