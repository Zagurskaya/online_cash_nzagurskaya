package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.UserDTO;

import java.sql.Connection;
import java.util.List;

public interface UserService {

    List<UserDTO> getAll(String where);

    List<UserDTO> getUsers();

    UserDTO add(UserDTO user);

    boolean delete(UserDTO user);

    boolean delete(Long id);

    boolean delete(List<UserDTO> users);

    boolean update(UserDTO user);

    UserDTO loadUserByUsername(String name);

}
