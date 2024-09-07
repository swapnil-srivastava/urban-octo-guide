package com.greenplate.greenplate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenplate.greenplate.model.Question;
import com.greenplate.greenplate.repository.QuestionRepository;

public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

}