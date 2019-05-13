package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.exception.UserServiceImplException;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

//public class UserServiceImpl extends AbstractServiceImpl<UserDTO> implements UserService {
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    //    private final DaoRepository daoRepository;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final ConnectionHandler connectionHandler;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository, ConnectionHandler connectionHandler, PasswordEncoder passwordEncoder) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.connectionHandler = connectionHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAll(String where) {
        try (Connection connection = connectionHandler.getConnection()) {
            return getAllWitchConnection(connection, where);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception with getAll users", e);
        }
    }

    private List<UserDTO> getAllWitchConnection(Connection connection, String where) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<User> users = userRepository.getAll(connection,where);
            List<UserDTO> dtos = users.stream()
                    .map(userConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception with getAll users", e);
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        try (Connection connection = connectionHandler.getConnection()) {
            return getUsersWitchConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception with getAll users", e);
        }
    }

    private List<UserDTO> getUsersWitchConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            List<User> users = userRepository.getUsers(connection);
            List<UserDTO> dtos = users.stream()
                    .map(userConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return dtos;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception with getAll users", e);
        }
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            return addWitchConnection(connection, userDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users", e);
        }
    }


    private UserDTO addWitchConnection(Connection connection, UserDTO userDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            User user = userConverter.toEntity(userDTO);
            String password = randomPasswordWithEncoder(6);
            user.setPassword(password);
            User added = userRepository.add(connection, user);
            UserDTO addedUser = userConverter.toDTO(added);
            connection.commit();
            return addedUser;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users witch Connection", e);
        }
    }

    @Override
    public boolean delete(UserDTO user) {
        try (Connection connection = connectionHandler.getConnection()) {
            return deleteWitchConnection(connection, user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users", e);
        }
    }

    private boolean deleteWitchConnection(Connection connection, UserDTO userDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            User user = userConverter.toEntity(userDTO);
            boolean deleted = userRepository.delete(connection, user);
            connection.commit();
            return deleted;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users witch Connection", e);
        }
    }

    @Override
    public boolean update(UserDTO user) {
        try (Connection connection = connectionHandler.getConnection()) {
            return updateWitchConnection(connection, user);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users", e);
        }
    }

    private boolean updateWitchConnection(Connection connection, UserDTO userDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            User user = userConverter.toEntity(userDTO);
            boolean deleted = userRepository.delete(connection, user);
            connection.commit();
            return deleted;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users witch Connection", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionHandler.getConnection()) {
            return deleteWitchConnection(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users", e);
        }
    }

    private boolean deleteWitchConnection(Connection connection, Long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            boolean deleted = userRepository.delete(connection, id);
            connection.commit();
            return deleted;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users witch Connection", e);
        }
    }
    @Override
    public boolean delete(List<UserDTO> usersDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            return deleteListUsersWitchConnection(connection, usersDTO);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users", e);
        }
    }

    private boolean deleteListUsersWitchConnection(Connection connection, List<UserDTO> usersDTO) throws SQLException {
        connection.setAutoCommit(false);
        try {
            boolean deletedList = usersDTO.stream()
                    .allMatch(s -> delete(s));
            connection.commit();
            return deletedList;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in add users witch Connection", e);
        }
    }

    @Override
    public UserDTO loadUserByUsername(String name) {
        try (Connection connection = connectionHandler.getConnection()) {
            return loadUserByUsernameWitchConnection(connection, name);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in load User By Username", e);
        }
    }

    private UserDTO loadUserByUsernameWitchConnection(Connection connection, String name) throws SQLException {
        connection.setAutoCommit(false);
        try {
            User loaded = userRepository.loadUserByUsername(connection, name);
            UserDTO userDTO = userConverter.toDTO(loaded);
            connection.commit();
            return userDTO;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new UserServiceImplException("Exception in load User By Username witch Connection", e);
        }
    }

    private String randomPasswordWithEncoder(int length) {
        String password = "";
        for (int i = 0; i < length; i++) {
            int oneNumber = (int) (Math.random() * 10);
            password += String.valueOf(oneNumber);
        }
        logger.info("New password => " + password);
        return encoder(password);
    }

    private String encoder(String word) {
        logger.info("New password encoder => " + passwordEncoder.encode(word));
        return passwordEncoder.encode(word);

    }

}
