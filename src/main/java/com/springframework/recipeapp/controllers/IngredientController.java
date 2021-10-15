package com.springframework.recipeapp.controllers;

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

    @RequestMapping("/recipe/{id}/ingredients")
    public String viewIngredients(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/ingredients/list";
    }

}
