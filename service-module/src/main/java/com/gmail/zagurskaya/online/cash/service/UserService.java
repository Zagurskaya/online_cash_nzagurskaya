package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.UserDTO;

import java.sql.Connection;
import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    UserDTO add(UserDTO user);

    boolean delete(Long id);

    boolean update(UserDTO user);

    UserDTO loadUserByUsername(String name);

    List<UserDTO> getActionUsersSortedByUserName();

    UserDTO getUserById(Long id);

    String returnPasswordSameAsLogin(UserDTO userDTO);

}
