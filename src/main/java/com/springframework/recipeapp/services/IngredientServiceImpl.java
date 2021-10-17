package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.converters.IngredientCommandToIngredient;
import com.springframework.recipeapp.converters.IngredientToIngredientCommand;
import com.springframework.recipeapp.domain.Ingredient;
import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IngredientServiceImpl implements  IngredientService{

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Autowired
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Autowired
    UnitOfMeasureService unitOfMeasureService;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        return recipeService.findById(recipeId).getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst()
                .get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Recipe recipe = recipeRepository.findById(ingredientCommand.getRecipeId()).get();
        Ingredient ingredient;
        if(ingredientCommand.getId()!=null) {
            ingredient = recipe.getIngredients().stream()
                    .filter(ingredientStream -> ingredientStream.getId().equals(ingredientCommand.getId()) )
                    .findFirst().get();
            ingredient.setUom(unitOfMeasureService.findById(ingredientCommand.getUom().getId()));
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setAmount(ingredientCommand.getAmount());
        }
        else{
            ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
            recipe.addIngredient(ingredient);
        }
        Recipe savedRecipe = recipeRepository.save(recipe);
        IngredientCommand savedIngredientCommand = savedRecipe.getIngredients().stream()
                .filter(ingredientStream -> ingredientStream.getAmount().equals(ingredientCommand.getAmount()))
                .filter(ingredientStream -> ingredientStream.getDescription().equals(ingredientCommand.getDescription()))
                .map(ingredientStream -> ingredientToIngredientCommand.convert(ingredientStream))
                .findFirst().get();
        return savedIngredientCommand;
    }

}
