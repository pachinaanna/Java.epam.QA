package com.epam.training.repository;

import com.epam.training.model.Zone_plant;
import com.epam.training.utils.DbConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Zone_plantRepository implements IRepository<Zone_plant> {

    final private DbConnectionManager dbConnectionManager;

    public Zone_plantRepository(DbConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    public boolean insert (Zone_plant zonePlant) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "INSERT INTO zone_plant (name, zone_id, quantity, infected_plants) VALUES (?,?,?,?)");
            prepState.setString(1, zonePlant.getName());
            prepState.setLong(2, zonePlant.getZoneId());
            prepState.setInt(3, zonePlant.getQuantity());
            prepState.setInt(4, zonePlant.getInfectedPlants());

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean delete(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "DELETE FROM zone_plant WHERE id = (?)");
            prepState.setLong(1, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public boolean update(String name, long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "UPDATE zone_plant SET name = (?) WHERE id = (?)");
            prepState.setString(1, name);
            prepState.setLong(2, id);

            return prepState.executeUpdate() > 0;
        }
        );
    }

    public Zone_plant read(long id) {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM zone_plant WHERE id = (?)");
            prepState.setLong(1, id);
            ResultSet resultSet = prepState.executeQuery();
            Zone_plant zp = new Zone_plant();
            while (resultSet.next()) {
                zp.setId(resultSet.getLong("id"));
                zp.setName(resultSet.getString("name"));
                zp.setZoneId(resultSet.getLong("zone_id"));
                zp.setQuantity(resultSet.getInt("quantity"));
                zp.setInfectedPlants(resultSet.getInt("infected_plants"));
            }
            return zp;
        }
        );
    }

    public List<Zone_plant> readAll() {
        return dbConnectionManager.doExecute(connection -> {
            PreparedStatement prepState = connection.prepareStatement(
                    "SELECT * FROM zone_plant");
            ResultSet resultSet = prepState.executeQuery();
            List<Zone_plant> listZonePlant = new ArrayList<>();
            while (resultSet.next()) {
                Zone_plant zp = new Zone_plant();
                zp.setId(resultSet.getLong("id"));
                zp.setName(resultSet.getString("name"));
                zp.setZoneId(resultSet.getLong("zone_id"));
                zp.setQuantity(resultSet.getInt("quantity"));
                zp.setInfectedPlants(resultSet.getInt("infected_plants"));

                listZonePlant.add(zp);

            }
            return listZonePlant;
        }
        );
    }
}


