package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.User;
import com.gmail.zagurskaya.online.cash.service.converter.UserConverter;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setRoleId(user.getRoleId());
        userDTO.setIsNotActive(user.getIsNotActive());
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setRoleId(userDTO.getRoleId());
        user.setIsNotActive(userDTO.getIsNotActive());
        return user;
    }
}
