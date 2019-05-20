package com.gmail.zagurskaya.online.cash.repository;

import com.gmail.zagurskaya.online.cash.repository.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends GenericRepository  {

//    List<User> getUsers(Connection connection);

//    User add(Connection connection, User user);
//
//    boolean delete(Connection connection, Long id);
//
//    boolean update(Connection connection, User user);

    User loadUserByUsername(Connection connection, String name);

    List<User> getActionUsersSortedByUserName(Connection connection);

//    User getUserById(Connection connection, Long id);
}
