package com.epam.training.model;

public abstract class BaseEntity {
    private long id;
    private String name;

    public BaseEntity() {
    }

    public BaseEntity(String name) {
    this.name = name;
    }

    public BaseEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
