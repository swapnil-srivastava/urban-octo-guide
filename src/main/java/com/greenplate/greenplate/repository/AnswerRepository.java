package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenplate.greenplate.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}
