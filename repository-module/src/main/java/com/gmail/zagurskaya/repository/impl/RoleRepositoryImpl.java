package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.exception.RoleRepositoryImplException;
import com.gmail.zagurskaya.repository.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//public abstract class RoleRepositoryImpl extends AbstractRepository implements RoleRepository {
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
    public Role getRole(Connection connection, long id) {
        return read(connection, id);
    }

    @Override
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

    @Override
    public Role read(Connection connection, Long id) {
        List<Role> roles = getAll(connection, " WHERE id=" + id);
        return roles.size() == 0 ? null : roles.get(0);
    }

    @Override
    public boolean update(Connection connection, Role role) {
        String sql = String.format(
                " UPDATE `roles` SET `name`='%s' WHERE `id`='%d'",
                role.getName(),
                role.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public boolean delete(Connection connection, Role role) {
        String sql = String.format(
                "DELETE FROM `roles` WHERE `id`='%d'",
                role.getId());
        return abstractRepository.executeUpdate(connection, sql);
    }

    @Override
    public List<Role> getAll(Connection connection) {
        return getAll(connection, "");
    }

    @Override
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

//    @Override
//    public List<Role> getRoles() {
//        List<Role> roleList = new ArrayList<>();
//        try (Connection connection = connectionHandler.getConnection()) {
//            try (Statement statement = connection.createStatement()) {
//
//                String sql = String.format("SELECT * FROM `role`");
//                ResultSet resultSet = statement.executeQuery(sql);
//                while (resultSet.next()) {
//                    Role role = new Role();
//                    role.setId(resultSet.getLong("id"));
//                    role.setName(resultSet.getString("name"));
//                    roleList.add(role);
//                }
//                return roleList;
//            } catch (SQLException e) {
//                throw new DatabaseConnectionException("Database exception during deleting All document", e);
//            }
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//            throw new DatabaseException("Database exception during connection", e);
//        }
//    }
}
