package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();

        if(ingredient.getId()!=null)
            ingredientCommand.setId(ingredient.getId());
        if(ingredient.getAmount()!=null)
            ingredientCommand.setAmount(ingredient.getAmount());
        if(ingredient.getDescription()!=null)
            ingredientCommand.setDescription(ingredient.getDescription());
        if(ingredient.getUom()!=null)
            ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
        if(ingredient.getRecipe()!=null)
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        return ingredientCommand;
    }
}
