package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeServiceImpl recipeService;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);

    }

    @Test
    public void mockMvc() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform( get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

    }

    @Test
    public void index() {



        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipes.add(recipe);
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipes);



        ArgumentCaptor<Set<Recipe>> argumentCaptor;
        argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String index = indexController.index(model);
        String expected = "index";
        assertEquals(expected,index);

        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2,setInController.size());
    }
}