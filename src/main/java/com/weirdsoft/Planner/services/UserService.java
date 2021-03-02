package com.weirdsoft.Planner.services;

import com.weirdsoft.Planner.models.User;

public interface UserService {
    User Login(String login, String password);
    User Register(String name, String login, String password);
}
