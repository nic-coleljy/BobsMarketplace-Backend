package com.bobsmarketplace.prototype.controller;

import com.bobsmarketplace.prototype.entity.Category;
import com.bobsmarketplace.prototype.exception.category.CategoryNotFoundException;
import com.bobsmarketplace.prototype.serviceImplementation.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    //Declaration of variables
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Instantiating the variables for this class.
     *
     * @param categoryServiceImpl
     */
    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    /**
     * Add a new category with POST request to "/admin"
     *
     * @param newCategory
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin")
    public Category addCategory(@Valid @RequestBody Category newCategory) {
        return categoryServiceImpl.save(newCategory);
    }

    /**
     * List all categories in the system
     *
     * @return list of all categories
     */
    @GetMapping("/")
    public List<Category> getCategory() {
        return categoryServiceImpl.getAllCategory();
    }

    /**
     * Search for category with the given catid
     * If there is no category with the given "catid", throw a CategoryNotFoundException
     *
     * @param catid
     * @return category with the given catid
     */
    @GetMapping("/find-by-id/{catid}")
    public Category getCategoryById(@PathVariable Long catid) throws CategoryNotFoundException {
        Category category = categoryServiceImpl.getCategoryById(catid);
        if (category == null) {
            throw new CategoryNotFoundException(catid);
        }
        return category;
    }

    /**
     * If there is no category with the given "name", throw a CategoryNotFoundException
     *
     * @param catid a Long value
     * @param newCategory a Category object containing the new category info to be updated
     * @return the updated, or newly added category
     */
    @PutMapping("/admin/{catid}")
    public Category updateCategory(@Valid @PathVariable Long catid, @RequestBody Category newCategory) throws CategoryNotFoundException {
        Category category = categoryServiceImpl.updateCategory(catid, newCategory);
        if (category == null) {
            throw new CategoryNotFoundException(catid);
        }

        return category;
    }

    /**
     * Remove a category with the DELETE request to "/admin/{name}"
     * If there is no category with the given "catid", throw a CategoryNotFoundException
     *
     * @param catid
     */
    @DeleteMapping("/admin/{catid}")
    public void deleteCategoryById(@PathVariable Long catid) throws
            CategoryNotFoundException {
        try {
            categoryServiceImpl.deleteCategoryById(catid);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException(catid);
        }
    }
}
