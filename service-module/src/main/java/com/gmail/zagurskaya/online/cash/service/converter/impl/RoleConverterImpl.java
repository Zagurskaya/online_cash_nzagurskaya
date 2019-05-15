package com.gmail.zagurskaya.online.cash.service.converter.impl;

import com.gmail.zagurskaya.online.cash.repository.model.Role;
import com.gmail.zagurskaya.online.cash.service.converter.RoleConverter;
import com.gmail.zagurskaya.online.cash.service.model.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }


}
