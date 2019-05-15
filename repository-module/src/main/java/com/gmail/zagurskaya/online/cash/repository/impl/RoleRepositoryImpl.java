package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.RoleRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.RoleRepositoryImplException;
import com.gmail.zagurskaya.online.cash.repository.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private static Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    private final AbstractRepository abstractRepository;

    public RoleRepositoryImpl(AbstractRepository abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    @Override
    public List<Role> getRoles(Connection connection) {
        return getAll(connection);
    }

    @Override
    public Role getRole(Connection connection, Long id) {
        List<Role> roles = getAll(connection, " WHERE id=" + id);
        return roles.size() == 0 ? null : roles.get(0);
    }

    public Role create(Connection connection, Role role) {
        String sql = String.format(
                "INSERT INTO `roles`(`id`, `name`) VALUES ('%d','%s')",
                role.getId(), role.getName());
        long roleId = abstractRepository.executeCreate(connection, sql);
        if (roleId > 0) {
            role.setId(roleId);
            return role;
        } else {
            return null;
        }
    }

    public List<Role> getAll(Connection connection) {
        return getAll(connection, "");
    }

    public List<Role> getAll(Connection connection, String where) {
        List<Role> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "SELECT * FROM `roles` " + where);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("id"));
                role.setName(resultSet.getString("name"));
                result.add(role);
            }
            return result;
        } catch (SQLException e) {
            throw new RoleRepositoryImplException("Database exception during getALL Role where" + where, e);
        }

    }

}
