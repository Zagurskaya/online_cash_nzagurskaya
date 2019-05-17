package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.UserRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.UserRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public User loadUserByUsername(Connection connection, String name) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getUsersFromResult(resultSet).get(0);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during  loadUserByUsername ", e);
        }
    }

    @Override
    public List<User> getActionUsersSortedByUserName(Connection connection) {
        String sql =
                "SELECT * FROM `users` WHERE `isNotActive`= 0 ORDER BY username";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            return getUsersFromResult(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during  getActionUsersSortedByUserName ", e);
        }
    }

    @Override
    public User getUserById(Connection connection, Long id) {
        String sql = "SELECT * FROM `users`  WHERE `id` = ? ";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getUsersFromResult(resultSet).get(0);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during  getUsersByID ", e);
        }
    }

    @Override
    public List<User> getUsers(Connection connection) {
        String sql = String.format(
                "SELECT * FROM `users`");
        List<User> userList = new ArrayList<>();
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            ResultSet resultSet = prepared.executeQuery(sql);
            return getUsersFromResult(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during  getUsers ", e);
        }
    }

    @Override
    public boolean delete(Connection connection, Long id) {
        String sql = "UPDATE users SET isNotActive = 1 WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return 1 == result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during  update user ", e);
        }

    }

    @Override
    public boolean update(Connection connection, User user) {
        String sql = "UPDATE users SET username=?, password=?, lastName=?, firstName=?, patronymic=?,  roleId=?, isNotActive=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getPatronymic());
            preparedStatement.setLong(6, user.getRoleId());
            preparedStatement.setLong(7, user.getIsNotActive() ? 1 : 0);
            preparedStatement.setLong(8, user.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return 1 == result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during  update user ", e);
        }
    }

    @Override
    public User add(Connection connection, User user) {
        String sql = "INSERT INTO users(username, password, lastName, firstName, patronymic, roleId, isNotActive) " +
                "VALUES (?, ?, ?, ?, ?, ?, '0')";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getPatronymic());
            preparedStatement.setLong(6, user.getRoleId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryException("Database exception during add user ", e);
        }
    }

    private List<User> getUsersFromResult(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setLastName(resultSet.getString("lastName"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setPatronymic(resultSet.getString("patronymic"));
            user.setRoleId(resultSet.getLong("roleId"));
            user.setIsNotActive(resultSet.getBoolean("isNotActive"));
            userList.add(user);
        }
        return userList;
    }
}
