package com.gmail.zagurskaya.online.cash.service;

import com.gmail.zagurskaya.online.cash.service.model.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> getRoles();

    RoleDTO getRole(Long id);

}
