package com.bobsmarketplace.prototype.serviceImplementation;

import com.bobsmarketplace.prototype.entity.Category;
import com.bobsmarketplace.prototype.entity.Seller;
import com.bobsmarketplace.prototype.exception.category.CategoryNotFoundException;
import com.bobsmarketplace.prototype.exception.seller.SellerNotFoundException;
import com.bobsmarketplace.prototype.repository.CategoryRepository;
import com.bobsmarketplace.prototype.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    //Declaration of variables
    private CategoryRepository categoryRepository;

    /**
     * Instantiating the variables for this class.
     *
     * @param categoryRepository
     */
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Persist a new category
     *
     * @param newCategory
     * @return Category that is saved into the database.
     */
    @Override
    public Category save(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    /**
     * Returns all the categories that is in the database
     *
     * @return List of categories
     */
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    /**
     * Finds a category based on its ID and returns the object if found in the database.
     *
     * @param catid
     * @return A category if it exists or else it will return null.
     */
    @Override
    public Category getCategoryById(Long catid) {
        return categoryRepository.findById(catid).orElse(null);
    }

    /**
     * Finds a category based on the catid and updates its contents with the data inside newCategory
     * @param catid
     * @param newCategory
     * @return The updated category or else return null
     */
    @Override
    public Category updateCategory(Long catid, Category newCategory) {
        return categoryRepository.findById(catid).map(category -> {
            category.setName(newCategory.getName());
            return categoryRepository.save(category);
        }).orElse(null);
    }

    /**
     * Remove a category with the given catid
     * Spring Data JPA does not return a value for delete operation
     * Cascading: removing a category will also remove all its associated information
     */
    @Override
    public void deleteCategoryById(Long catid) {
        categoryRepository.deleteById(catid);
    }
}
