package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Role;

import java.sql.Connection;
import java.util.List;


public interface RoleRepository extends DaoRepository<Role>{

    List<Role> getRoles(Connection connection);

    Role getRole(Connection connection, long id);
    }
