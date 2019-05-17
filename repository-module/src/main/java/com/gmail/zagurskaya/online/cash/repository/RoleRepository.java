package com.gmail.zagurskaya.online.cash.repository;

import com.gmail.zagurskaya.online.cash.repository.model.Role;

import java.sql.Connection;
import java.util.List;


public interface RoleRepository {

    List<Role> getRoles(Connection connection);

    Role getRole(Connection connection, Long id);

    }
