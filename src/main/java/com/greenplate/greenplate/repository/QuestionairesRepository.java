package com.greenplate.greenplate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenplate.greenplate.model.Questionaires;

@Repository
public interface QuestionairesRepository extends JpaRepository<Questionaires, Long> {
    
    List<Questionaires> findByQuestionOrder(Integer questionOrder);
    List<Questionaires> findByQuestionName(String questionName);

}
