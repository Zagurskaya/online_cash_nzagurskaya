package com.gmail.zagurskaya.service.security;
import com.gmail.zagurskaya.service.model.RoleDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class AppUserPrincipal implements UserDetails {

    private UserDTO user;
    private Set<RoleDTO> roles;
    private Set<GrantedAuthority> grantedAuthorities;

    public AppUserPrincipal(UserDTO userDto, Set<RoleDTO> roles) {
        this.user = userDto;
        this.roles = roles;
        this.grantedAuthorities = roles.stream().map(roleDTO -> new SimpleGrantedAuthority(roleDTO.getName())).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
