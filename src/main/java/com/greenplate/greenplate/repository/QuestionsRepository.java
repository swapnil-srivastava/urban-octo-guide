package com.greenplate.greenplate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenplate.greenplate.model.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    
    List<Questions> findByQuestionText(String questionText);

}
