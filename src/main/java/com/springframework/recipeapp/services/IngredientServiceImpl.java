package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.converters.IngredientToIngredientCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements  IngredientService{

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        return recipeService.findById(recipeId).getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst()
                .get();
    }
}
