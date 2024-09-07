package com.greenplate.greenplate.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name="hello-world")
@Data
@NoArgsConstructor
public class HelloWorldModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String email;

    public HelloWorldModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
