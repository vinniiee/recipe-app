package com.springframework.recipeapp.repositories;

import com.springframework.recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepositories extends CrudRepository<UnitOfMeasure,Long> {
}
