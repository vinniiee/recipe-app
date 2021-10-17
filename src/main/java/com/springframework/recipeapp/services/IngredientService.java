package com.springframework.recipeapp.services;


import com.springframework.recipeapp.commands.IngredientCommand;

public interface IngredientService  {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
