package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.services.IngredientService;
import com.springframework.recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {


    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientService ingredientService;


    @RequestMapping("/recipe/{id}/ingredient")
    public String viewIngredients(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",ingredientService
                                    .findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }


}
