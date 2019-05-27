package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.UserDTO;

import java.sql.Connection;
import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    void add(UserDTO user);

    void delete(Long id);

    void update(UserDTO user);

    UserDTO loadUserByUsername(String name);

    List<UserDTO> getActionUsersSortedByUserName();

    UserDTO getUserById(Long id);

    String returnPasswordSameAsLogin(UserDTO userDTO);

}
