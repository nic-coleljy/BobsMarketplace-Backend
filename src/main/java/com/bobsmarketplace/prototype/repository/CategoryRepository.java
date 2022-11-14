package com.bobsmarketplace.prototype.repository;

import com.bobsmarketplace.prototype.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
