package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenplate.greenplate.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
