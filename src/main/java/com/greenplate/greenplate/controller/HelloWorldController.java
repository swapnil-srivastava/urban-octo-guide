package com.greenplate.greenplate.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.greenplate.greenplate.model.HelloWorldModel;
import com.greenplate.greenplate.services.HelloWorldService;

@RestController
@CrossOrigin(origins = "https://curly-succotash-web.vercel.app/")
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;
    
    @Value("${app.version}")
    private String appVersion;
    
    private RestTemplate restTemplate; 

    @GetMapping("/hello-spring")
    public String HelloWorldSprings() {
        String url = "https://guess-a-number.azurewebsites.net/hack/0";
        String response = restTemplate.getForObject(url, String.class);
        return "response and " + response;
    }

    @GetMapping("/hello-world")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-list")
    public List<HelloWorldModel> HelloWorldList() {
        return helloWorldService.findAllHelloWorld();
    }

    @GetMapping("/load-list")
    public List<HelloWorldModel> getMethodName() {
        List<HelloWorldModel> helloWorldList = new ArrayList<HelloWorldModel>(List.of(
            new HelloWorldModel("Hello Swapnil Srivastava", "hello@swapnil.com"),
            new HelloWorldModel("Hello Valentin Braukmann", "hello@valentin.com"),
            new HelloWorldModel("Hello Florian Alexander Nathan Loher", "hello@florian.com"),
            new HelloWorldModel("Hello Juliana Cerqueira", "hello@juliana.com")
        ));

        return helloWorldService.saveAllService(helloWorldList);
    }
    
}
