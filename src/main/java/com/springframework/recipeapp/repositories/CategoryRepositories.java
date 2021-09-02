package com.springframework.recipeapp.repositories;

import com.springframework.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepositories extends CrudRepository<Category,Long> {
}
