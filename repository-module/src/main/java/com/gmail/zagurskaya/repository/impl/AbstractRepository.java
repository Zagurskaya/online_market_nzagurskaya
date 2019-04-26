package com.gmail.zagurskaya.repository.impl;

import java.sql.*;
import java.util.Properties;
import javax.annotation.PostConstruct;

import com.gmail.zagurskaya.repository.ConnectionRepository;
import com.gmail.zagurskaya.repository.exception.DatabaseConnectionException;
import com.gmail.zagurskaya.repository.exception.DatabaseException;
import com.gmail.zagurskaya.repository.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class AbstractRepository implements ConnectionRepository {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private final DatabaseProperties databaseProperties;

    public AbstractRepository(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
        try {
            Class.forName(databaseProperties.getDatabaseDriverName());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", databaseProperties.getDatabaseUsername());
            properties.setProperty("password", databaseProperties.getDatabasePassword());
            properties.setProperty("useUnicode", "true");
            properties.setProperty("useJDBCCompliantTimezoneShift", "true");
            properties.setProperty("useLegacyDatetimeCode", "false");
            properties.setProperty("serverTimezone", "UTC");
            return DriverManager.getConnection(databaseProperties.getDatabaseURL(), properties);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseConnectionException("Database connection error", e);
        }
    }

    @PostConstruct
    public void initializeDatabase() {
        try (Connection connection = getConnection()) {
            processSQL(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseConnectionException("Database connection error", e);
        }
    }

    private void processSQL(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `online_market`.`Item` (\n" +
                            "  `id` INT NOT NULL,\n" +
                            "  `name` VARCHAR(80) NULL,\n" +
                            "  `status` VARCHAR(45) NULL,\n" +
                            "  PRIMARY KEY (`id`))\n" +
                            "ENGINE = InnoDB;\n");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `online_market`.`role` (\n" +
                            "  `id` INT NOT NULL,\n" +
                            "  `name` VARCHAR(20) NULL,\n" +
                            "  PRIMARY KEY (`id`))\n" +
                            "ENGINE = InnoDB;");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `online_market`.`user` (\n" +
                            "  `id` INT NOT NULL,\n" +
                            "  `username` VARCHAR(70) NULL,\n" +
                            "  `password` VARCHAR(200) NULL,\n" +
                            "  `roleId` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`),\n" +
                            "  INDEX `fk_user_role_idx` (`roleId` ASC),\n" +
                            "  CONSTRAINT `fk_user_role`\n" +
                            "    FOREIGN KEY (`roleId`)\n" +
                            "    REFERENCES `online_market`.`role` (`id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB;");
            statement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }
    protected boolean executeUpdate(String sql) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                int result = statement.executeUpdate(sql);
                connection.commit();
                return 1 == result;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new DatabaseException("Database exception during update document", e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during connection", e);
        }
    }

    protected long executeCreate(String sql) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                int count = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                connection.commit();
                if (count == 1) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
                return 0;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new DatabaseException("Database exception during insert document", e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Database exception during connection", e);
        }
    }
}
