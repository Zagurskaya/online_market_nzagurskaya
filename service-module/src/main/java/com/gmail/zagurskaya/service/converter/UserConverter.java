package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.model.UserDTO;

public interface UserConverter {

    UserDTO toDTO(User item);

    User toEntity(UserDTO item);
}
