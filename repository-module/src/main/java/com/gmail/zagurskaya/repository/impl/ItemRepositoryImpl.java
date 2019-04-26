package com.gmail.zagurskaya.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.zagurskaya.repository.ItemRepository;
import com.gmail.zagurskaya.repository.connection.ConnectionHandler;
import com.gmail.zagurskaya.repository.exception.DatabaseConnectionException;
import com.gmail.zagurskaya.repository.exception.DatabaseException;
import com.gmail.zagurskaya.repository.model.Item;
import com.gmail.zagurskaya.repository.model.ItemStatusEnum;
import com.gmail.zagurskaya.repository.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ItemRepositoryImpl extends AbstractRepository implements ItemRepository {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    private ConnectionHandler connectionHandler;

    @Autowired
    public ItemRepositoryImpl(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    @Override
    public List<Item> getItems() {
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = connectionHandler.getConnection()) {
            try (Statement statement = connection.createStatement()) {

                String sql = String.format("SELECT * FROM `item`");
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getLong("id"));
                    item.setName(resultSet.getString("name"));
                    item.setStatus(ItemStatusEnum.valueOf(resultSet.getString("status")));
                    itemList.add(item);
                }
                return itemList;
            } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
                throw new DatabaseConnectionException("Database exception during deleting All document", e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during connection", e);
        }
    }
}
