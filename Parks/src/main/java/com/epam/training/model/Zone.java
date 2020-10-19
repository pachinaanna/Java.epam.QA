package com.epam.training.model;

public class Zone extends BaseEntity {

    long parkId;
    int number;

    public Zone() {

    }

    public Zone(String name, long parkId, int number) {
        super(name);
        this.parkId = parkId;
        this.number = number;
    }

    public Zone(long id, String name, long parkId, int number) {
        super(id, name);
        this.parkId = parkId;
        this.number = number;
    }

    public long getParkId() {
        return parkId;
    }

    public void setParkId(long parkId) {
        this.parkId = parkId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
