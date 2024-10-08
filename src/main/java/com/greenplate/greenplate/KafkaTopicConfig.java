package com.greenplate.greenplate;

import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean 
    public KafkaAdmin admin() {
      return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers));
    }

    @Bean
    public NewTopic supermanTopic() {
      return TopicBuilder.name("superman-topic")
          .partitions(6)
          .replicas(3)
          .compact()
          .build();
    }

    @Bean
    public NewTopic streamsWordcountOutput() {
      return TopicBuilder.name("streams-wordcount-output")
          .partitions(6)
          .replicas(3)
          .compact()
          .build();
    }
  
}
