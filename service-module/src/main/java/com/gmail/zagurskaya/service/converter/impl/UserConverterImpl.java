package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;

public class UserConverterImpl implements UserConverter {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleId(user.getRoleId());
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO user) {
        User userDTO = new User();
        userDTO.setId(userDTO.getId());
        userDTO.setUsername(userDTO.getUsername());
        userDTO.setPassword(userDTO.getPassword());
        userDTO.setRoleId(userDTO.getRoleId());
        return userDTO;
    }
}
