package com.epam.training.model;

public class Gardener extends BaseEntity {
    long parkId;

    public Gardener() {

    }
    public Gardener(String name, long parkId) {
    super(name);
    this.parkId = parkId;
    }

    public Gardener(long id, String name, long parkId) {
        super(id, name);
        this.parkId = parkId;
    }

    @Override
    public String toString() {
        return "Gardener{" + "id= '" + super.getId() + '\'' +
                "name= '" + super.getName() + '\'' +
                "parkId= '" + parkId +
                '}' + '\n';
    }

    public long getParkId() {
        return parkId;
    }

    public void setParkId(long parkId) {
        this.parkId = parkId;
    }
}
