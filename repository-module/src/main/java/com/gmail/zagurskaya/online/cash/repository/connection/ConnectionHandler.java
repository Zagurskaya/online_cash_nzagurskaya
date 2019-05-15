package com.gmail.zagurskaya.online.cash.repository.connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.gmail.zagurskaya.online.cash.repository.exception.DatabaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import com.gmail.zagurskaya.online.cash.repository.exception.DatabaseException;
import com.gmail.zagurskaya.online.cash.repository.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionHandler {

    private static Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);

    private final DatabaseProperties databaseProperties;

    @Autowired
    public ConnectionHandler(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.getDatabaseDriverName());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        this.databaseProperties = databaseProperties;
    }

    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", databaseProperties.getDatabaseUsername());
            properties.setProperty("password", databaseProperties.getDatabasePassword());
            properties.setProperty("useUnicode", "true");
            properties.setProperty("useJDBCCompliantTimezoneShift", "true");
            properties.setProperty("useLegacyDatetimeCode", "false");
            properties.setProperty("serverTimezone", "UTC");
            properties.setProperty("characterEncoding", "cp1251");
            return DriverManager.getConnection(databaseProperties.getDatabaseURL(), properties);
        } catch (SQLException e) {
            logger.error("Connection Failed!", e);
            throw new DatabaseConnectionException("Connection Failed!", e);
        }
    }

    @PostConstruct
    public void initializeDatabase() {
        String initialFileName = getClass().getResource("/" + databaseProperties.getDatabaseInitialFile()).getPath().substring(1);
        List<String> commandsCreateList = readCommandsFromSqlToList(initialFileName, "CREATE");
        List<String> commandsInsertList = readCommandsFromSqlToList(initialFileName, "INSERT");
        ReadCommand(readCommandsCreateAndDropSchema());
        ReadCommand(commandsCreateList);
        ReadCommand(commandsInsertList);
    }

    private void ReadCommand(List<String> commandList) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                for (String command : commandList) {
                    statement.addBatch(command);
                }
                statement.executeBatch();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                logger.error("SQL Exception ! ", e);
                throw new DatabaseConnectionException("SQL Exception", e);
            }
        } catch (SQLException e) {
            logger.error("Connection Failed!", e);
            throw new DatabaseConnectionException("Connection Failed!", e);
        }
    }

    private List<String> readCommandsFromSqlToList(String initialFileName, String commandWord) {
        try (Stream<String> text = Files.lines(Paths.get(initialFileName))) {
            return Arrays.stream(text.collect(Collectors.joining())
                    .replaceAll(commandWord, ";" + commandWord)
                    .split(";")).map(s -> s + ";")
                    .filter(s -> s.startsWith(commandWord))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Error while reading database initial file", e);
        }
    }

    private List<String> readCommandsCreateAndDropSchema() {
        List<String> arraysCommandsCreateAndDropSchema = new ArrayList<>();
        String drop = "DROP SCHEMA IF EXISTS `zagurskaya_cash`;";
        String create = "CREATE SCHEMA IF NOT EXISTS `zagurskaya_cash` DEFAULT CHARACTER SET utf8;";
        arraysCommandsCreateAndDropSchema.add(drop);
        arraysCommandsCreateAndDropSchema.add(create);
        return arraysCommandsCreateAndDropSchema;
    }
}
