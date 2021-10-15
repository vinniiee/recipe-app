package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService  {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);

}
