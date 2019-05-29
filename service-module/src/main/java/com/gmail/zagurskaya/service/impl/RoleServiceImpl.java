package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.model.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleConverter roleConverter, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll(0, Integer.MAX_VALUE);
        List<RoleDTO> rolesDTO = roles.stream()
                .map(roleConverter::toDTO)
                .collect(Collectors.toList());
        return rolesDTO;
    }

    @Override
    @Transactional
    public RoleDTO getRole(Long id) {
        Role role = (Role) roleRepository.findById(id);
        RoleDTO roleDTO = roleConverter.toDTO(role);
        return roleDTO;
    }
}
