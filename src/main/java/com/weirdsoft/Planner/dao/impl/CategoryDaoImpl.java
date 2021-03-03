package com.weirdsoft.Planner.dao.impl;

import com.weirdsoft.Planner.dao.CategoryDao;
import com.weirdsoft.Planner.dao.DaoUtils;
import com.weirdsoft.Planner.dao.NoteDao;
import com.weirdsoft.Planner.mappers.CategoryMapper;
import com.weirdsoft.Planner.mappers.NoteMapper;
import com.weirdsoft.Planner.models.Category;
import com.weirdsoft.Planner.models.Note;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class CategoryDaoImpl implements CategoryDao {
    private static final String getAllSql = "select id, name, color from Categories";

    @Override
    public List<Category> getAll() {
        return DaoUtils.executeAndMap(getAllSql, CategoryMapper::mapCategories, (ps) -> {});
    }

    @Override
    public Category find(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category create(Category object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UUID delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UUID update(Category object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
