package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.exception.RoleServiceImplException;
import com.gmail.zagurskaya.service.model.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//public class RoleServiceImpl extends AbstractServiceImpl<RoleDTO> implements RoleService {
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;
    //    private final DaoRepository daoRepository;
    private final ConnectionHandler connectionHandler;


    public RoleServiceImpl(RoleConverter roleConverter, RoleRepository roleRepository, ConnectionHandler connectionHandler) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<RoleDTO> getRoles() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getRolesWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleServiceImplException("Exception with getAll roles", e);
        }
    }

    private List<RoleDTO> getRolesWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<Role> roles = roleRepository.getRoles(connection);
            List<RoleDTO> rolesDTO = roles.stream()
                    .map(roleConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return rolesDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RoleServiceImplException("Exception with getAll roles", e);
        }
    }

    @Override
    public RoleDTO getRole(long id) {
        try (Connection connection = connectionHandler.getConnection()) {
            return getRoleWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RoleServiceImplException("Exception in get Role Witch Connection ", e);
        }
    }

    private RoleDTO getRoleWitchConnection(Connection connection, long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            Role role = roleRepository.getRole(connection, id);
            RoleDTO roleDTO = roleConverter.toDTO(role);
            connection.commit();
            return roleDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new RoleServiceImplException("Exception in read role witch ID", e);
        }
    }

//    @Override
//    public RoleDTO create(Connection connection, RoleDTO roleDTO) {
//        Role role = roleConverter.toEntity(roleDTO);
//        Role createRole = (Role) daoRepository.create(connection, role);
//        return roleConverter.toDTO(createRole);
//    }
//
//    @Override
//    public RoleDTO read(Connection connection, long id) {
//        Role readRole = (Role) daoRepository.read(connection, id);
//        return roleConverter.toDTO(readRole);
//    }
//
//    @Override
//    public boolean update(Connection connection, RoleDTO roleDTO) {
//        Role role = roleConverter.toEntity(roleDTO);
//        return daoRepository.update(connection, role);
//    }
//
//    @Override
//    public boolean delete(Connection connection, RoleDTO roleDTO) {
//        Role role = roleConverter.toEntity(roleDTO);
//        return daoRepository.delete(connection, role);
//    }
//
//    @Override
//    public List<RoleDTO> getAll(Connection connection) {
//        List<Role> roles = daoRepository.getAll(connection);
//        List<RoleDTO> dtos = roles.stream()
//                .map(roleConverter::toDTO)
//                .collect(Collectors.toList());
//        return dtos;
//    }
//
//    @Override
//    public List<RoleDTO> getAll(Connection connection, String where) {
//        List<Role> roles = daoRepository.getAll(connection, where);
//        List<RoleDTO> dtos = roles.stream()
//                .map(roleConverter::toDTO)
//                .collect(Collectors.toList());
//        return dtos;
//    }

//    @Override
//    public List<RoleDTO> getRoles() {
//
//        List<Role> roles = roleRepository.getRoles();
//        List<RoleDTO> dtos = roles.stream()
//                .map(roleConverter::toDTO)
//                .collect(Collectors.toList());
//        return dtos;
//    }
}
