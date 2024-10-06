package com.greenplate.greenplate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String key, String message) {
        System.out.println("KafkaProducerService :::: "+ " " + "topic " + topic + " key " + key + " message "+ message);
        kafkaTemplate.send(topic, key ,message);
    }
    
}
