package com.gmail.zagurskaya.service.security;


import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.exception.UsernameNotFoundException;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.service.model.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AppUserDetailService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws org.springframework.security.core.userdetails.UsernameNotFoundException {
        UserDTO userDTO = userService.loadUserByUsername(name);
        RoleDTO roleDTO = roleService.getRole(userDTO.getRoleId());
        if (userDTO == null) {
            throw new UsernameNotFoundException("User with name = " + name +" and role "+ roleDTO.getName()+ "not found");
        }
        return new AppUserPrincipal(userDTO, roleDTO);
    }
}
