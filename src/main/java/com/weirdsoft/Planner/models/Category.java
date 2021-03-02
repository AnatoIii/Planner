package com.weirdsoft.Planner.models;

import java.util.UUID;

public class Category {
    private UUID userId;
    private String name;
    private String color;

    public Category(UUID userId, String name, String color) {
        this.userId = userId;
        this.name = name;
        this.color = color;
    }

    public Category(){}

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
