package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.services.ImageService;
import com.springframework.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class ImageController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    ImageService imageService;

    @GetMapping("/recipe/{recipeId}/image")
    public String uploadForm(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(Long.valueOf(recipeId),file);
        log.debug("wabalabadubdub");
        return "redirect:/recipe/"+recipeId+"/show";
    }

}
