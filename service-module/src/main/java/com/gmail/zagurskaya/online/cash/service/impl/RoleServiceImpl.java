package com.gmail.zagurskaya.online.cash.service.impl;

import com.gmail.zagurskaya.online.cash.repository.RoleRepository;
import com.gmail.zagurskaya.online.cash.repository.model.Role;
import com.gmail.zagurskaya.online.cash.service.RoleService;
import com.gmail.zagurskaya.online.cash.service.converter.RoleConverter;
import com.gmail.zagurskaya.online.cash.service.exception.RoleServiceException;
import com.gmail.zagurskaya.online.cash.service.model.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleConverter roleConverter, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getRoles() {
        try (Connection connection = roleRepository.getConnection()) {
            return getRolesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleServiceException("Exception with getAll roles", e);
        }
    }

    private List<RoleDTO> getRolesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<Role> roles = roleRepository.findAll(0, Integer.MAX_VALUE);
            List<RoleDTO> rolesDTO = roles.stream()
                    .map(roleConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return rolesDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RoleServiceException("Exception with getAll roles", e);
        }
    }

    @Override
    public RoleDTO getRole(Long id) {
        try (Connection connection = roleRepository.getConnection()) {
            return getRoleWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleServiceException("Exception in get Role Witch Connection ", e);
        }
    }

    private RoleDTO getRoleWitchConnection(Connection connection, long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Role role = (Role) roleRepository.findById( id);
            RoleDTO roleDTO = roleConverter.toDTO(role);
            connection.commit();
            return roleDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RoleServiceException("Exception in read role witch ID", e);
        }
    }
}
