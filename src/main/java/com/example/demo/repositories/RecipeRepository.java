package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
