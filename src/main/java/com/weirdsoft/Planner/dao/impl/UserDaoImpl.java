package com.weirdsoft.Planner.dao.impl;

import com.weirdsoft.Planner.dao.DaoUtils;
import com.weirdsoft.Planner.dao.UserDao;
import com.weirdsoft.Planner.mappers.NoteMapper;
import com.weirdsoft.Planner.mappers.UserMapper;
import com.weirdsoft.Planner.models.User;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class UserDaoImpl implements UserDao {
    private static final String getByIdSql = "select id, name, login, passwordHash, salt from Users where id = ?";
    private static final String getByLoginSql = "select id, name, login, passwordHash, salt from Users where login = ?";
    private static final String deleteByIdSql = "delete from Users where id = ? RETURNING id";
    private static final String createSql = "insert into Users(id, name, login, passwordHash, salt) VALUES (?, ?, ?, ?, ?) RETURNING id";
    private static final String updateSql = "update Users set salt=? WHERE userId=? RETURNING id";
    
    @Override
    public User find(UUID id) {
        return DaoUtils.executeAndMap(getByIdSql, UserMapper::mapUser, (ps) -> {
            try {
                ps.setString(1, id.toString());
            } catch (SQLException e) {

            }
        });
    }

    @Override
    public User create(User object) {
        UUID newId = UUID.randomUUID();
        DaoUtils.executeAndMap(createSql,DaoUtils::uuidMapper,(ps) -> {
            try {
                ps.setString(1, newId.toString());
                ps.setString(2, object.getName());
                ps.setString(3, object.getLogin());
                ps.setString(4, object.getPasswordHash());
                ps.setString(5, object.getSalt());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        object.setUserId(newId);
        return object;
    }

    @Override
    public UUID delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UUID update(User object) {
        return DaoUtils.executeAndMap(updateSql, DaoUtils::uuidMapper, (ps) -> {
            try {
                ps.setString(1, object.getPasswordHash());
                ps.setString(2, object.getUserId().toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public User getUserByLogin(String login) {        
        return DaoUtils.executeAndMap(getByLoginSql, UserMapper::mapUser, (ps) -> {
            try {
                ps.setString(1, login);
            } catch (SQLException e) {

            }
        });
    }
}
