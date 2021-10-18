package com.springframework.recipeapp.controllers;


import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showRecipeById(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id , Model model){

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId()+"/show/";
    }


}
