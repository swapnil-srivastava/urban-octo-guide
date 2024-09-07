package com.greenplate.greenplate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenplate.greenplate.model.HelloWorldModel;
import com.greenplate.greenplate.repository.HelloWorldRepository;

@Service
public class HelloWorldService {

    @Autowired
    HelloWorldRepository helloWorldRepository;

    public List<HelloWorldModel> findAllHelloWorld() {
        return helloWorldRepository.findAll();
    }

    public HelloWorldModel saveHelloWorld(HelloWorldModel helloWorldModel) {
        return helloWorldRepository.save(helloWorldModel);
    }

}
