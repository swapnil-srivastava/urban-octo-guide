package com.greenplate.greenplate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenplate.greenplate.model.Questions;
import com.greenplate.greenplate.repository.QuestionsRepository;

public class QuestionairesService {

    @Autowired
    QuestionsRepository questionsRepository;

    public List<Questions> findAllQuestions() {
        return questionsRepository.findAll();
    }

}