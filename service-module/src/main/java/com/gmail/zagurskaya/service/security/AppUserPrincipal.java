package com.gmail.zagurskaya.service.security;

import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.impl.RoleServiceImpl;
import com.gmail.zagurskaya.service.model.RoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class AppUserPrincipal implements UserDetails {

    private User user;
    private Set<GrantedAuthority> grantedAuthorities;
    private final RoleServiceImpl roleService;


    public AppUserPrincipal(User user) {
        this.user = user;
        this.grantedAuthorities = new HashSet<>();
        for (RoleDTO role : getUserRoles(user,roleService)) {
            if (user.getRoleId() == role.getId()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentiaIsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    private List<RoleDTO> getUserRoles(User user, RoleServiceImpl roleService) {
        List<RoleDTO> userRolesList = new ArrayList<RoleDTO>();
        for (RoleDTO role : roleService.getRoles()) {
            if (user.getRoleId() == role.getId()) {
                userRolesList.add(role);
            }
        }
        return userRolesList;
    }
}
