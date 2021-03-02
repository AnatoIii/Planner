package com.weirdsoft.Planner.mappers;

import com.weirdsoft.Planner.models.Note;
import com.weirdsoft.Planner.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper {
    public static User mapUser(ResultSet rs) {
        User user = null;
        try {
            if(rs.next()){
                user = new User();
                user.setUserId(UUID.fromString(rs.getString("userId")));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPasswordHash(rs.getString("passwordHash"));
                user.setSalt(rs.getString("salt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
