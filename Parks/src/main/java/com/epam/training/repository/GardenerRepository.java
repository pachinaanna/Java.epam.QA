package com.epam.training.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.epam.training.model.*;
import com.epam.training.utils.DbConnectionManager;

public class GardenerRepository implements IRepository<Gardener> {

    final private DbConnectionManager dbConnectionManager;

    public GardenerRepository(DbConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    public boolean insert(Gardener gardener) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "INSERT INTO gardener (name, park_id) VALUES (?,?)");
            prepState.setString(1, gardener.getName());
            prepState.setLong(2, gardener.getParkId());

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean delete(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "DELETE FROM gardener WHERE id = (?)");
            prepState.setLong(1, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean update(String name, long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "UPDATE gardener SET name = (?) WHERE id = (?)");
            prepState.setString(1, name);
            prepState.setLong(2, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public Gardener read(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM gardener WHERE id = (?)");
            prepState.setLong(1, id);
            ResultSet resultSet = prepState.executeQuery();
            Gardener g = new Gardener();
            while (resultSet.next()) {
                g.setId(resultSet.getLong("id"));
                g.setName(resultSet.getString("name"));
                g.setParkId(resultSet.getLong("park_id"));
            }
            return g;
        }
        );
    }

    public List<Gardener> readAll() {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM gardener");
            ResultSet resultSet = prepState.executeQuery();
            List<Gardener> listGardener = new ArrayList<>();
            while (resultSet.next()) {
                Gardener g = new Gardener();
                g.setId(resultSet.getLong("id"));
                g.setName(resultSet.getString("name"));
                g.setParkId(resultSet.getLong("park_id"));
                listGardener.add(g);

            }
            return listGardener;
        }
        );
    }

}


