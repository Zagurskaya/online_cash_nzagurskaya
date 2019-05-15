package com.gmail.zagurskaya.online.cash.service.security;
import com.gmail.zagurskaya.online.cash.repository.DaoRepository;
import com.gmail.zagurskaya.online.cash.service.RoleService;
import com.gmail.zagurskaya.online.cash.service.impl.RoleServiceImpl;
import com.gmail.zagurskaya.online.cash.service.model.RoleDTO;
import com.gmail.zagurskaya.online.cash.service.model.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUserPrincipal implements UserDetails {

    private UserDTO user;
    private RoleDTO role;
    private Set<GrantedAuthority> grantedAuthorities;

    public AppUserPrincipal(UserDTO userDto, RoleDTO role) {
        this.user = userDto;
        this.role = role;
        this.grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
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
