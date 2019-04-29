package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.User;
import java.util.List;

public interface UserRepository {

    public List<User> getUsers();

    User add(User user);

    User loadUserByUsername(String name);
}
