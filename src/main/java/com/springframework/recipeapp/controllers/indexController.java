package com.springframework.recipeapp.controllers;


import com.springframework.recipeapp.domain.Category;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import com.springframework.recipeapp.repositories.CategoryRepositories;
import com.springframework.recipeapp.repositories.UnitOfMeasureRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class indexController {

    private CategoryRepositories categoryRepositories;
    private UnitOfMeasureRepositories unitOfMeasureRepositories;


    public indexController(CategoryRepositories categoryRepositories, UnitOfMeasureRepositories unitOfMeasureRepositories) {
        this.categoryRepositories = categoryRepositories;
        this.unitOfMeasureRepositories = unitOfMeasureRepositories;
    }

    @RequestMapping("/test")
    String test(){

        Optional<Category> categor = categoryRepositories.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasur = unitOfMeasureRepositories.findByDescription("Cup");

        System.out.println(categor.get().getId());
        System.out.println(unitOfMeasur.get().getId());

        return "index";
    }


    @RequestMapping({"","/","/index"})
    String index(){
        return "index";
    }

}
