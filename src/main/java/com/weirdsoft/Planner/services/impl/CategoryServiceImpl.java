package com.weirdsoft.Planner.services.impl;

import com.weirdsoft.Planner.dao.CategoryDao;
import com.weirdsoft.Planner.models.Category;
import com.weirdsoft.Planner.services.CategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Inject
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
}
