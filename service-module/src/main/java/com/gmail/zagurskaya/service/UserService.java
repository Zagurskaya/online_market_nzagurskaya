package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> getUsers();

    UserDTO add(UserDTO user);

    UserDTO loadUserByUsername(String name);

}
