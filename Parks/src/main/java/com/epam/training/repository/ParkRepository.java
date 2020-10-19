package com.epam.training.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.epam.training.model.*;
import com.epam.training.utils.DbConnectionManager;

public class ParkRepository implements IRepository<Park> {

    final private DbConnectionManager dbConnectionManager;

    public ParkRepository(DbConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    public boolean insert(Park park) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "INSERT INTO park (name, square, location) VALUES (?,?,?)");
            prepState.setString(1, park.getName());
            prepState.setDouble(2, park.getSquare());
            prepState.setString(3, park.getLocation());

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean delete(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "DELETE FROM park WHERE id = (?)");
            prepState.setLong(1, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean update(String name, long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "UPDATE park SET name = (?) WHERE id = (?)");
            prepState.setString(1, name);
            prepState.setLong(2, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public Park read(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM park WHERE id = (?)");
            prepState.setLong(1, id);
            ResultSet resultSet = prepState.executeQuery();
            Park p = new Park();
            while (resultSet.next()) {
                p.setId(resultSet.getLong("id"));
                p.setName(resultSet.getString("name"));
                p.setSquare(resultSet.getDouble("square"));
                p.setLocation(resultSet.getString("location"));
            }
            return p;
        }
        );
    }

    public List <Park> readAll() {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM park");
            ResultSet resultSet = prepState.executeQuery();
            List<Park> listPark = new ArrayList<>();
            while (resultSet.next()) {
                Park p = new Park();
                p.setId(resultSet.getLong("id"));
                p.setName(resultSet.getString("name"));
                p.setSquare(resultSet.getDouble("square"));
                p.setLocation(resultSet.getString("location"));
                listPark.add(p);
            }
            return listPark;
        }
        );
    }

    public boolean clearTable(String sql) {
        return dbConnectionManager.doExecute(connection -> {
                    Statement state = connection.createStatement();
                    return state.execute(sql);
                }
        );
    }

}