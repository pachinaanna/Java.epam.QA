package com.epam.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionManager {

    private final String dbUrl;
    private final String user;
    private final String password;
    private final String driverClass;

    public DbConnectionManager(String dbUrl, String user, String password, String driverClass) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
        this.driverClass = driverClass;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        return DriverManager.getConnection(dbUrl, user, password);
    }

    public interface Function<T, R> {
        R apply(T t) throws SQLException;
    }


    public <T> T doExecute(Function<Connection, T> func) {
        try (Connection connection = getConnection()) {
            return func.apply(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}