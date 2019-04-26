package com.gmail.zagurskaya.service.security;

import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.exception.UsernameNotFoundException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Service;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@Service
public class AppUserDetailService implements UserDetailsService{


    private  final UserService userService;

    public AppUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.loadUserByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException("User is not found");
        }

        return new AppUserPrincipal(user);
    }
}
