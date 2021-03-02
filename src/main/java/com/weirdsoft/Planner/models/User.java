package com.weirdsoft.Planner.models;

import java.util.UUID;

public class User {
    private UUID userId;
    private String name;
    private String login;
    private String passwordHash;
    private String salt;

    public User(UUID userId, String name, String login, String passwordHash, String salt) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public User(){}

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
