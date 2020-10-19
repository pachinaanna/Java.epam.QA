package com.epam.training.model;

public class Zone_plant extends BaseEntity {

    long zoneId;
    int quantity;
    int infectedPlants;

    public Zone_plant() {

    }

    public Zone_plant(long id, String name, long zoneId, int quantity, int infectedPlants) {
        super(id, name);
        this.zoneId = zoneId;
        this.quantity = quantity;
        this.infectedPlants = infectedPlants;
    }

    public Zone_plant(String name, long zoneId, int quantity, int infectedPlants) {
        super(name);
        this.zoneId = zoneId;
        this.quantity = quantity;
        this.infectedPlants = infectedPlants;
    }

    public long getZoneId() {
        return zoneId;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInfectedPlants() {
        return infectedPlants;
    }

    public void setInfectedPlants(int infectedPlants) {
        this.infectedPlants = infectedPlants;
    }
}
