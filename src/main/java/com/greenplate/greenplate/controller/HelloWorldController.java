package com.greenplate.greenplate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings("unchecked")
    @GetMapping("/version")
    public Map getAppVersion() {
        @SuppressWarnings("rawtypes")
        Map map = new HashMap();
        map.put("app-version", appVersion);
        return map;
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
        HelloWorldModel helloWorldModel = new HelloWorldModel();

        helloWorldModel.setName("Hello Swapnil");
        helloWorldModel.setEmail("hello@swapnil.de");

        return helloWorldService.saveHelloWorld(helloWorldModel);
    }
}
