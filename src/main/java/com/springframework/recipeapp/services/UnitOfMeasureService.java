package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.UnitOfMeasureCommand;
import com.springframework.recipeapp.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
    UnitOfMeasure findById(Long id);
}
