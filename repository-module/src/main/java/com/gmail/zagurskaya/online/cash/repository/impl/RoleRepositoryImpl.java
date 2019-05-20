package com.gmail.zagurskaya.online.cash.repository.impl;

import com.gmail.zagurskaya.online.cash.repository.RoleRepository;
import com.gmail.zagurskaya.online.cash.repository.exception.RoleRepositoryException;
import com.gmail.zagurskaya.online.cash.repository.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl implements RoleRepository {

    private static Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

//    @Override
//    public List<Role> getRoles(Connection connection) {
//        String sql = String.format(
//                "SELECT * FROM `roles`");
//        List<Role> roleList = new ArrayList<>();
//        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
//            ResultSet resultSet = prepared.executeQuery(sql);
//            return getRolesFromResult(resultSet);
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//            throw new RoleRepositoryException("Database exception during  getRoles ", e);
//        }
//    }

//    @Override
//    public Role getRole(Connection connection, Long id) {
//        String sql = "SELECT * FROM `roles`  WHERE `id` = ? ";
//        List<Role> roleList = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return getRolesFromResult(resultSet).get(0);
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//            throw new RoleRepositoryException("Database exception during  get Role By ID ", e);
//        }
//    }

//    private List<Role> getRolesFromResult(ResultSet resultSet) throws SQLException {
//        List<Role> roleList = new ArrayList<>();
//        while (resultSet.next()) {
//            Role role = new Role();
//            role.setId(resultSet.getLong("id"));
//            role.setName(resultSet.getString("name"));
//            roleList.add(role);
//        }
//        return roleList;
//    }
}
