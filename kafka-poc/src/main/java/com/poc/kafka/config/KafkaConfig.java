package com.poc.kafka.config;

import com.poc.kafka.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic kafkaPaymentTopic() {
        return TopicBuilder.name(KafkaConstants.TOPIC_PAYMENT)
                .partitions(3)
                .replicas(1)
                .build();
    }

    /** Topic For Integration Test */
    @Bean
    public NewTopic kafkaTestTopic() {
        return TopicBuilder.name(KafkaConstants.TOPIC_TEST)
                .build();
    }
}
