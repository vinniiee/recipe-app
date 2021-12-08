package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.domain.Category;
import com.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    private final CategoryToCategoryCommand categoryConveter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConveter, IngredientToIngredientCommand ingredientConverter,
                                 NotesToNotesCommand notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        if(source.getId()!=null)
            command.setId(source.getId());

        if(source.getCookTime()!=null)
            command.setCookTime(source.getCookTime());

        if(source.getPrepTime()!=null)
            command.setPrepTime(source.getPrepTime());

        if(source.getDescription()!=null)
            command.setDescription(source.getDescription());

        if(source.getDifficulty()!=null)
            command.setDifficulty(source.getDifficulty());

        if(source.getDirections()!=null)
            command.setDirections(source.getDirections());

        if(source.getServings()!=null)
            command.setServings(source.getServings());

        if(source.getSource()!=null)
            command.setSource(source.getSource());

        if(source.getUrl()!=null)
            command.setUrl(source.getUrl());

        if(source.getImg()!=null)
            command.setImg(source.getImg());

        if(source.getNotes()!=null)
            command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
