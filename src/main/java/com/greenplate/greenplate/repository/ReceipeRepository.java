package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenplate.greenplate.model.Recipe;

public interface ReceipeRepository extends JpaRepository<Recipe, Long> {
    
}
