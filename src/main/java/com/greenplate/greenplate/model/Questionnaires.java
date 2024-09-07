package com.greenplate.greenplate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity  // Make sure to mark it as an Entity
@Table(name = "questionnaires")  // Ensure correct table name matching your DB
public class Questionnaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String questionName;
    private Integer questionOrder;

    @Column(name = "answer_options")
    private String answerOptions; 

    public List<String> getAnswerOptionsList() {
        if (answerOptions == null || answerOptions.isEmpty()) {
            return List.of(); // Return an empty list if answerOptions is null or empty
        }
        return Arrays.asList(answerOptions.split(","));
    }

    public void setAnswerOptionsList(List<String> options) {
        if (options == null || options.isEmpty()) {
            this.answerOptions = ""; // Handle empty or null input
        } else {
            this.answerOptions = String.join(",", options);
        }
    }
}
