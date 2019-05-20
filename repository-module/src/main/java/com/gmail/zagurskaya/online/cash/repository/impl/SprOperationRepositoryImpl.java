package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.SprOperationRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.SprOperationRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.SprOperations;
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
public class SprOperationRepositoryImpl extends GenericRepositoryImpl implements SprOperationRepository {

    private static Logger logger = LoggerFactory.getLogger(SprOperationRepositoryImpl.class);

    @Override
    public List<SprOperations> getSprOperations(Connection connection) {
        String sql = String.format(
                "SELECT * FROM `sprOperations`");
        List<SprOperations> sprOperationList = new ArrayList<>();
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            ResultSet resultSet = prepared.executeQuery(sql);
            return getSprOperationsFromResult(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SprOperationRepositoryException("Database exception during  getSprOperations ", e);
        }
    }

    private List<SprOperations> getSprOperationsFromResult(ResultSet resultSet) throws SQLException {
        List<SprOperations> sprOperationList = new ArrayList<>();
        while (resultSet.next()) {
            SprOperations sprOperation = new SprOperations();
            sprOperation.setId(resultSet.getLong("id"));
            sprOperation.setName(resultSet.getString("name"));
            sprOperation.setSpecification(resultSet.getString("specification"));
            sprOperationList.add(sprOperation);
        }
        return sprOperationList;
    }

}
