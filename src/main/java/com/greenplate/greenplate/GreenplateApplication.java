package com.greenplate.greenplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GreenplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreenplateApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> { 
			String url = "https://guess-a-number.azurewebsites.net/hack/0";
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("response ::::::", response);
		};
	}

}
