package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.model.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.reflect.generics.repository.AbstractRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;
    private final ConnectionHandler connectionHandler;


    public RoleServiceImpl(RoleConverter roleConverter, RoleRepository roleRepository, ConnectionHandler connectionHandler) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public List<RoleDTO> getRoles() {

        List<Role> roles = roleRepository.getRoles();
        List<RoleDTO> dtos = roles.stream()
                .map(roleConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }
}
