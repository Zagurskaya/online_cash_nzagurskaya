package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.User;

import java.util.List;

public interface UserRepository extends GenericRepository<Long, User>  {

    User loadUserByUsername(String name);

    List<User> getActionUsersSortedByUserName();
}
