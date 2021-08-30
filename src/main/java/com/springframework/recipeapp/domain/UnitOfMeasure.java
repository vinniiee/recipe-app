package com.springframework.recipeapp.domain;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String uom;

    @OneToOne
    Ingredient ingredient;
}
