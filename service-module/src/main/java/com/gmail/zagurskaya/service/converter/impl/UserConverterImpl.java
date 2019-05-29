package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.repository.model.UserInfo;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;

    public UserConverterImpl(RoleConverter roleConverter, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(roleConverter.toDTO(user.getRole()));
        userDTO.setRoleId(user.getRole().getId());
        if (user.getUserInfo() != null) {
            userDTO.setLastName(user.getUserInfo().getLastName());
            userDTO.setFirstName(user.getUserInfo().getFirstName());
            userDTO.setPatronymic(user.getUserInfo().getPatronymic());
        }
        userDTO.setIsNotActive(user.getIsNotActive());

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(roleRepository.findById(userDTO.getRoleId()));

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userDTO.getFirstName());
        userInfo.setLastName(userDTO.getLastName());
        userInfo.setPatronymic(userDTO.getPatronymic());
        user.setUserInfo(userInfo);
        userInfo.setUser(user);

        user.setIsNotActive(userDTO.getIsNotActive());
        return user;
    }
}
