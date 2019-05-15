package com.gmail.zagurskaya.online.cash.service.converter;

import com.gmail.zagurskaya.online.cash.repository.model.Role;
import com.gmail.zagurskaya.online.cash.service.model.RoleDTO;

public interface RoleConverter {

    RoleDTO toDTO(Role role);

    Role toEntity(RoleDTO roleDTO);

}
