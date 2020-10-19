package com.epam.training.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.epam.training.model.*;
import com.epam.training.utils.DbConnectionManager;

public class ZoneRepository implements IRepository<Zone> {

    final private DbConnectionManager dbConnectionManager;

    public ZoneRepository(DbConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    public boolean insert (Zone zone) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "INSERT INTO zone (name, park_id, number) VALUES (?,?,?)");
            prepState.setString(1, zone.getName());
            prepState.setLong(2, zone.getParkId());
            prepState.setInt(3, zone.getNumber());

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean delete(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "DELETE FROM zone WHERE id = (?)");
            prepState.setLong(1, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean update(String name, long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "UPDATE zone SET name = (?) WHERE id = (?)");
            prepState.setString(1, name);
            prepState.setLong(2, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public Zone read(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM zone WHERE id = (?)");
            prepState.setLong(1, id);
            ResultSet resultSet = prepState.executeQuery();
            Zone z = new Zone();
            while (resultSet.next()) {
                z.setId(resultSet.getLong("id"));
                z.setName(resultSet.getString("name"));
                z.setParkId(resultSet.getLong("park_id"));
                z.setNumber(resultSet.getInt("number"));
            }
            return z;
        }
        );
    }

    public List<Zone> readAll() {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM zone");
            ResultSet resultSet = prepState.executeQuery();
            List<Zone> listZone = new ArrayList<>();
            while (resultSet.next()) {
                Zone z = new Zone();
                z.setId(resultSet.getLong("id"));
                z.setParkId(resultSet.getLong("park_id"));
                z.setNumber(resultSet.getInt("number"));
                z.setName(resultSet.getString("name"));

                listZone.add(z);

            }
            return listZone;
        }
        );
    }
}
