package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.converters.IngredientToIngredientCommand;
import com.springframework.recipeapp.domain.Ingredient;
import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements  IngredientService{

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientToIngredientCommand ingredientToIngredientCommand;

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
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe by given id not found: "+ingredientCommand.getRecipeId());
        }
            Recipe recipe = recipeOptional.get();

        Ingredient ingredient = recipe.getIngredients()
                .stream()
                .filter(ingredientStream -> ingredientStream.getId().equals(ingredientCommand.getId()))
                .findFirst()
                .get();
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUom(unitOfMeasureService.findById(ingredientCommand.getUom().getId()));
        recipeRepository.save(recipe);
        return ingredientToIngredientCommand.convert(ingredient);
    }


}
