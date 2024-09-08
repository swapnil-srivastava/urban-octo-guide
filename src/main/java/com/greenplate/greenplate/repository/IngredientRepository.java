package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenplate.greenplate.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    
}
