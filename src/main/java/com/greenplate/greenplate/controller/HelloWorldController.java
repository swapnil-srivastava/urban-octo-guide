package com.greenplate.greenplate.controller;

import java.util.List;
import java.util.UUID;
import java.time.Duration;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.javafaker.Faker;
import com.greenplate.greenplate.model.HelloWorldModel;
import com.greenplate.greenplate.services.HelloWorldService;
import com.greenplate.greenplate.services.KafkaProducerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RestController
@CrossOrigin(origins = "https://curly-succotash-web.vercel.app/")
@RequiredArgsConstructor
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private WebClient webClient;

    @Autowired
    private final Faker faker;
    
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
        return "Hello World with kafka stream";
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
    public Mono<ResponseEntity<String>> callGcpService() {
        return webClient.get()
            .uri("/message")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, 
                response -> Mono.error(new RuntimeException("4xx error")))
            .onStatus(HttpStatusCode::is5xxServerError, 
                response -> Mono.error(new RuntimeException("5xx error")))
            .toEntity(String.class)
            .timeout(Duration.ofSeconds(10));
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
    
    @GetMapping("/hello-kafka/{key}/{message}")
    public ResponseEntity<String> sendKafkaMessage(@PathVariable("key") String key, @PathVariable("message") String message) {
        kafkaProducerService.sendMessage("hello_world_topic", key ,message);
        return ResponseEntity.ok("Message sent to Kafka topic: " + message);
    }

    @PostMapping("/generate/shakespeare")
    public String generateShakespeareQuote() {
        String id = UUID.randomUUID().toString();
        String quote = faker.shakespeare().hamletQuote();
        kafkaProducerService.sendMessage("shakespeare-topic", id, quote);
        return "Shakespeare message sent to Kafka";
    }

    @PostMapping("/generate-batch/shakespeare")
    public String generateShakespeareQuoteBatch(@RequestParam(defaultValue = "10") int batchSize) {
        List<String> sentMessages = new ArrayList<>();

        for (int i = 0; i < batchSize; i++) {
            String id = UUID.randomUUID().toString();
            String quote = faker.shakespeare().hamletQuote();
            kafkaProducerService.sendMessage("shakespeare-topic", id, quote);
            sentMessages.add("ID: " + id + ", Quote: " + quote);
        }

        return String.format("Batch of %d Shakespeare messages sent to Kafka. Messages:\n%s", 
                             batchSize, String.join("\n", sentMessages));
    }

    @PostMapping("/generate/backToTheFuture")
    public String generateBackToTheFutureQuote() {
        String quote = faker.backToTheFuture().quote();
        String character = faker.backToTheFuture().character();
        kafkaProducerService.sendMessage("back-to-the-future-topic", character, quote);
        return "back-to-the-future-topic message sent to Kafka";
    }

    @PostMapping("/generate-batch/backToTheFuture")
    public String generateBackToTheFutureQuoteBatch(@RequestParam(defaultValue = "10") int batchSize) {
        List<String> sentMessages = new ArrayList<>();

        for (int i = 0; i < batchSize; i++) {
            String quote = faker.backToTheFuture().quote();
            String character = faker.backToTheFuture().character();
            kafkaProducerService.sendMessage("back-to-the-future-topic", character, quote);
            sentMessages.add("Character: " + character + ", Quote: " + quote);
        }

        return String.format("Batch of %d back-to-the-future-topic messages sent to Kafka. Messages:\n%s", 
                             batchSize, String.join("\n", sentMessages));
    }
}
