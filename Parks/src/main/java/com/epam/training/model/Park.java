package com.epam.training.model;

public class Park extends BaseEntity {

    String location;
    double square;

    public Park(String name, double square, String location) {
        super(name);
        this.square = square;
        this.location = location;

    }

    public Park () {

    }

    public Park(long id, String name, double square, String location) {
        super(id, name);
        this.square = square;
        this.location = location;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Park{" + "id= '" + super.getId() + '\'' +
                "name= '" + super.getName() + '\'' +
                "location= '" + location + '\'' +
                "square= '" + square +
                '}' + '\n';
    }
}


