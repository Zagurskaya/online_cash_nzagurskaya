package com.gmail.zagurskaya.repository.impl;

import java.sql.*;

import com.gmail.zagurskaya.repository.ConnectionRepository;
import com.gmail.zagurskaya.repository.exception.DatabaseException;
import com.gmail.zagurskaya.repository.properties.DatabaseProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
//abstract class AbstractRepository implements ConnectionRepository {
@Repository
public class AbstractRepository {

    private static Logger logger = LoggerFactory.getLogger(AbstractRepository.class);
    private final DatabaseProperties databaseProperties;

    public AbstractRepository(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
        try {
            Class.forName(databaseProperties.getDatabaseDriverName());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }


    protected boolean executeUpdate(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(sql);
            return 1 == result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during update ", e);
        }
    }

    protected long executeCreate(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (count == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
            return 0;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during insert ", e);
        }
    }
}
