package com.greenplate.greenplate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenplate.greenplate.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
