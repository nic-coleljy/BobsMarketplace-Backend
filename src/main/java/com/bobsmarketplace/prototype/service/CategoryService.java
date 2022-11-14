package com.bobsmarketplace.prototype.service;

import com.bobsmarketplace.prototype.entity.Category;
import java.util.List;

public interface CategoryService {
    //CREATE
    Category save(Category newCategory);

    //READ
    List<Category> getAllCategory();

    Category getCategoryById(Long catid);

    //UPDATE
    Category updateCategory(Long catid, Category newCategory);

    //DELETE
    void deleteCategoryById(Long catid);
}
