package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.repository.AbstractRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final ConnectionHandler connectionHandler;

    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository, ConnectionHandler connectionHandler) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.getUsers();
        List<UserDTO> dtos = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        User added = userRepository.add(user);
        return userConverter.toDTO(added);
    }

    @Override
    public UserDTO loadUserByUsername(String name) {
        User loaded = userRepository.loadUserByUsername(name);
        return userConverter.toDTO(loaded);
    }
}
