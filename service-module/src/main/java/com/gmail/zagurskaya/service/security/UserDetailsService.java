package com.gmail.zagurskaya.service.security;

public interface UserDetailsService {

    public UserDetails loadUserByUsername(String username);
}
