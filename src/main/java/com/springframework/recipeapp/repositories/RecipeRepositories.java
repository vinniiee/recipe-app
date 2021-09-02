package com.springframework.recipeapp.repositories;

import com.springframework.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepositories extends CrudRepository<Recipe,Long> {
}
