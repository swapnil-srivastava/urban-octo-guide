package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.greenplate.greenplate.model.HelloWorldModel;

@Repository
public interface HelloWorldRepository extends JpaRepository<HelloWorldModel, Long> {

    public List<HelloWorldModel> findByName(String name);
    public List<HelloWorldModel> findByEmail(String email);
    

    
}
