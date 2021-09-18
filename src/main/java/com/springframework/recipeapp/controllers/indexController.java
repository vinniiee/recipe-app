package com.springframework.recipeapp.controllers;


import com.springframework.recipeapp.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    private final RecipeServiceImpl recipeService;

    public indexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    String index(Model model){

        model.addAttribute("recipes",recipeService.getRecipes());

        return "index";
    }

}
