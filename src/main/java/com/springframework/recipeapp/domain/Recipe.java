package com.springframework.recipeapp.domain;

import com.springframework.recipeapp.enums.Difficulty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"categories","ingredients","notes"})
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private Byte[] img;
    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients= new HashSet<Ingredient>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name="recipe_category",
                joinColumns = @JoinColumn( name = "recipe_id"),
                inverseJoinColumns = @JoinColumn( name = "category_id"))
    private Set<Category> categories = new HashSet<Category>();


    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

}
