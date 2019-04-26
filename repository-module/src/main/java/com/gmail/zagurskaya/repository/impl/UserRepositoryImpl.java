package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.exception.DatabaseConnectionException;
import com.gmail.zagurskaya.repository.exception.DatabaseException;
import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.repository.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class UserRepositoryImpl extends AbstractRepository implements UserRepository {
    
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private ConnectionHandler connectionHandler;
    
    @Autowired
    public UserRepositoryImpl(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionHandler.getConnection()) {
            try (Statement statement = connection.createStatement()) {

                String sql = String.format("SELECT * FROM `user`");
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("status"));
                    userList.add(user);
                }
                return userList;
            } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
                throw new DatabaseConnectionException("Database exception during deleting All user", e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during connection", e);
        }
    }

    @Override
    public User add(User user) {
        String sql = String.format("INSERT INTO `user`(`username`, `password`, `roleId`) VALUES ('%s','%s','%s')",
                user.getUsername(), user.getPassword(), user.getRoleId());

        long userId = executeCreate(sql);
        if (userId > 0) {
            user.setId(userId);
            return user;
        } else {
            return null;
        }
    }
    public User loadUserByUsername(String name) {
        List<Role> roles = getRoles();
        User user = new User();
        try (Connection connection = connectionHandler.getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sql = String.format("SELECT * FROM `user` WHERE `username`='%s'", name);
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                connection.commit();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    for (Role role : roles) {
                        if(role.getId()==resultSet.getLong("rolesId")){
                            user.setRoleId(role.getId());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during connection", e);
        }
        return user;
    }

    public List<Role> getRoles() {
        List<Role> roleList = new ArrayList<>();
        try (Connection connection = connectionHandler.getConnection()) {
            try (Statement statement = connection.createStatement()) {

                String sql = String.format("SELECT * FROM `role`");
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setId(resultSet.getLong("id"));
                    role.setName(resultSet.getString("name"));
                    roleList.add(role);
                }
                return roleList;
            } catch (SQLException e) {
                throw new DatabaseConnectionException("Database exception during deleting All document", e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during connection", e);
        }
    }
}
