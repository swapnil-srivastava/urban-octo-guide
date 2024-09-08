package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenplate.greenplate.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> { 
    
}
