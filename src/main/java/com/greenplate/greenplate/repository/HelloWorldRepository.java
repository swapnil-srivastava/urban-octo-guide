package com.greenplate.greenplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.greenplate.greenplate.model.HelloWorldModel;

@Repository
public interface HelloWorldRepository extends JpaRepository<HelloWorldModel, Long> {

    List<HelloWorldModel> findByName(String name);
    List<HelloWorldModel> findByEmail(String email);
    List<HelloWorldModel> findByNameContaining(String name);
    List<HelloWorldModel> findByEmailContaining(String email);
    List<HelloWorldModel> findByNameContainingOrEmailContaining(String name, String email);
    
}
