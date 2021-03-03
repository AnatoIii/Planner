package com.weirdsoft.Planner.models;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private String color;

    public Category(UUID id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Category(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
