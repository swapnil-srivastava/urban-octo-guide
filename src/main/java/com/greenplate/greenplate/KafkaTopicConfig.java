package com.greenplate.greenplate;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}") 
    private String bootstrapAddress;

    @Value("${spring.kafka.properties.sasl.mechanism}") 
    private String saslMechanism;

    @Value("${spring.kafka.properties.sasl.jaas.config}") 
    private String salsJaasConfig;

    @Value("${spring.kafka.properties.security.protocol}") 
    private String securityProtocol;

    @Value("${spring.kafka.properties.sasl.jaas.config}") 
    private String saslJaasConfig;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        configs.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
        configs.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic backToTheFuture() {
      return TopicBuilder.name("back-to-the-future-topic")
          .partitions(6)
          .replicas(3)
          .compact()
          .build();
    }
  
}
