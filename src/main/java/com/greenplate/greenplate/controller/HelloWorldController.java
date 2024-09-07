package com.greenplate.greenplate.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenplate.greenplate.model.HelloWorldModel;
import com.greenplate.greenplate.services.HelloWorldService;

@RestController
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;
    
    @Value("${app.version}")
    private String appVersion; 

    @GetMapping("/hello-spring")
    public String HelloWorldSprings() {
        return "Hello World Spring";
    }

    @GetMapping("/hello-world")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-list")
    public List<HelloWorldModel> HelloWorldList() {
        return helloWorldService.findAllHelloWorld();
    }

    @GetMapping("/load-hello-world")
    public HelloWorldModel HelloWorldLoad() {
        HelloWorldModel helloWorldModel = new HelloWorldModel(null, "Hello Swapnil Srivastava", "hello@swapnil.de");

        helloWorldModel.setName("Hello Swapnil Srivastava");
        helloWorldModel.setEmail("hello@swapnil.de");

        return helloWorldService.saveHelloWorld(helloWorldModel);
    }

    @GetMapping("/load-list")
    public List<HelloWorldModel> getMethodName() {
        List<HelloWorldModel> helloWorldList = new ArrayList<HelloWorldModel>(List.of(
            new HelloWorldModel((long) 1, "Hello Swapnil Srivastava", "hello@swapnil.com"),
            new HelloWorldModel((long) 2, "Hello Valentin Braukmann", "hello@valentin.com"),
            new HelloWorldModel((long) 3, "Hello Florian Alexander Nathan Loher", "hello@florian.com"),
            new HelloWorldModel((long) 4, "Hello Juliana Cerqueira", "hello@juliana.com")
        ));

        return helloWorldService.saveAllService(helloWorldList);
    }
    
}
