package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.domain.Ingredient;
import com.springframework.recipeapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Autowired
    private RecipeRepository recipeRepository;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        if(source.getId()!=null)
            ingredient.setId(source.getId());

        if (source.getAmount()!=null)
            ingredient.setAmount(source.getAmount());

        if(source.getDescription()!=null)
            ingredient.setDescription(source.getDescription());

        if(source.getUom()!=null)
            ingredient.setUom(uomConverter.convert(source.getUom()));

        if(source.getRecipeId()!=null)
            ingredient.setRecipe(recipeRepository.findById(source.getRecipeId()).get());

        return ingredient;
    }
}