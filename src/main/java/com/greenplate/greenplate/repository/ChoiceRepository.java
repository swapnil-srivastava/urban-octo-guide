package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenplate.greenplate.model.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    
}
