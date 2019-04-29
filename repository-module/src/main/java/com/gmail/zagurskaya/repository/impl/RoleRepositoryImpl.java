package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.RoleRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.exception.DatabaseConnectionException;
import com.gmail.zagurskaya.repository.exception.DatabaseException;
import com.gmail.zagurskaya.repository.model.Role;
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
public abstract class RoleRepositoryImpl extends AbstractRepository implements RoleRepository {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private ConnectionHandler connectionHandler;

    @Autowired
    public RoleRepositoryImpl(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    @Override
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
