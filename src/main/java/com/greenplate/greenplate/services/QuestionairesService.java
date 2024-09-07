package com.greenplate.greenplate.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenplate.greenplate.model.Questionaires;
import com.greenplate.greenplate.repository.QuestionairesRepository;

public class QuestionairesService {

    @Autowired
    QuestionairesRepository questionairesRepository;

    public List<Questionaires> findAllQuestions() {
        return questionairesRepository.findAll();
    }

    public Optional<Questionaires> findById(Long id) {
        return questionairesRepository.findById(id);
    }

}