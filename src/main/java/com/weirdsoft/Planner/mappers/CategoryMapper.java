package com.weirdsoft.Planner.mappers;

import com.weirdsoft.Planner.models.Category;
import com.weirdsoft.Planner.models.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryMapper {
    public static Category mapCategory(ResultSet rs) {
        Category category = null;
        try {
            if(rs.next()){
                category = new Category();
                category.setId(UUID.fromString(rs.getString("id")));
                category.setName(rs.getString("name"));
                category.setColor(rs.getString("color"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public static List<Category> mapCategories(ResultSet rs){
        List<Category> categories = new ArrayList<>();
        try {
        while(rs.next()){
            Category category = new Category();
            category.setId(UUID.fromString(rs.getString("id")));
            category.setName(rs.getString("name"));
            category.setColor(rs.getString("color"));
            categories.add(category);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
