package com.greenplate.greenplate.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${app.version}")
    private String appVersion;

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

    @GetMapping(value = "/call-heroku-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMessage() {
        return "Hello from the heroku";
    }

    @GetMapping("/call-guess-number-service")
    public ResponseEntity<String> callGuessNumber() {
        String guessNumber2Url = "https://guess-a-number.azurewebsites.net/hack/0";
        
        ResponseEntity<String> response = restTemplate.getForEntity(guessNumber2Url, String.class);
        String message = response.getBody();
        
        return ResponseEntity.ok("Message from guess number service: " + message);
    }

    @GetMapping("/call-gcp-service")
    public ResponseEntity<String> callGcpService() {
        String gcpService2Url = "https://swapnilsrivastava-eu-service-181557558593.europe-west3.run.app/message";
        
        ResponseEntity<String> response = restTemplate.getForEntity(gcpService2Url, String.class);
        String message = response.getBody();
        
        return ResponseEntity.ok("Message from GCP Service: " + message);
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
