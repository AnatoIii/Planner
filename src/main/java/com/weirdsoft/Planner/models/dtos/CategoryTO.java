/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weirdsoft.Planner.models.dtos;

import java.util.UUID;

public class CategoryTO {
    private UUID categoryId;
    private String name;
    
    public CategoryTO(String name, UUID categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
    
    public UUID getCategoryId() {
        return categoryId;
    }
}
