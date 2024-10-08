package com.greenplate.greenplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafka
@EnableKafkaStreams
public class GreenplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreenplateApplication.class, args);
	}

}
