package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.exception.UserRepositoryImplException;
import com.gmail.zagurskaya.repository.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public abstract class UserRepositoryImpl extends AbstractRepository implements UserRepository {
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public UserRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    @Override
    public User loadUserByUsername(Connection connection, String name) {
        String where = String.format(
                " WHERE `username`='%s'",
                name);
        return getAll(connection, where).get(0);
    }

    @Override
    public List<User> getUsers(Connection connection) {
        return getAll(connection);
    }

    @Override
    public User add(Connection connection, User user) {

        return create(connection, user);
    }

    @Override
    public User create(Connection connection, User user) {
        String sql = String.format("INSERT INTO `users`(`username`, `password`, `lastName`, `firstName`, `patronymic`, `email`, `roleId`, `isNotActive`) " +
                        "VALUES ('%s','%s','%s','%s','%s','%s','%s', '%s')",
                user.getUsername(), user.getPassword(), user.getLastName(), user.getFirstName(), user.getPatronymic(), user.getEmail(), user.getRoleId(), user.getIsNotActive() ? 1 : 0);

        long userId = abstractRepository.executeCreate(connection, sql);
        if (userId > 0) {
            user.setId(userId);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User read(Connection connection, Long id) {
        List<User> users = getAll(connection, " WHERE id=" + id);
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public boolean update(Connection connection, User user) {
        String sql = String.format(
                "UPDATE `users` SET `username`='%s', `password`='%s', `lastName`='%s', `firstName`='%s', `patronymic`='%s', `email`='%s', `roleId`='%d', `isNotActive`='%d' WHERE `id`='%d'",
                user.getUsername(), user.getPassword(), user.getLastName(), user.getFirstName(), user.getPatronymic(), user.getEmail(), user.getRoleId(), user.getIsNotActive() ? 1 : 0, user.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, User user) {
        String sql = String.format(
                "DELETE FROM `users` WHERE `id`='%d'",
                user.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, Long id) {
//        String sql = String.format(
//                "DELETE FROM `users` WHERE `id`='%d'",
//                id);
        String sql = String.format(
                "UPDATE `users` SET `isNotActive`='%d' WHERE `id`='%d'",
                1, id);

        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public List<User> getAll(Connection connection) {
        return getAll(connection, "");
    }

    @Override
    public List<User> getAll(Connection connection, String where) {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `users`" + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setLastName(resultSet.getString("lastName"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleId(resultSet.getLong("roleId"));
                user.setIsNotActive(resultSet.getBoolean("isNotActive"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserRepositoryImplException("Database exception during getAll user where " + where, e);
        }

    }

}
