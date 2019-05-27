package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleConverterImpl implements RoleConverter {
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setUsers(role.getUsers().stream().map(user -> userConverter.toDTO(user)).collect(Collectors.toSet()));
        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setUsers(roleDTO.getUsers().stream().map(userDTO -> userRepository.findById(userDTO.getId())).collect(Collectors.toSet()));

        return role;
    }


}
