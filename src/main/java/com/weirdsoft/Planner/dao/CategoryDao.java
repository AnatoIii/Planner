package com.weirdsoft.Planner.dao;

import com.weirdsoft.Planner.models.Category;
import java.util.List;

public interface CategoryDao extends GenericDao<Category>{
    List<Category> getAll();
}
