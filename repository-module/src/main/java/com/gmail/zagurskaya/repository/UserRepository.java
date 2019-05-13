package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends DaoRepository<User>{

    List<User> getAll(Connection connection, String where);

    List<User> getUsers(Connection connection);

    User add(Connection connection, User user);

    boolean delete(Connection connection, User user);

    boolean delete(Connection connection, Long id);

    boolean update(Connection connection, User user);

    User loadUserByUsername(Connection connection, String name);
}
