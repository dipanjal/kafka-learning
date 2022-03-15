package com.poc.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.kafka.config.KafkaConfig;
import com.poc.kafka.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class POCProducer<T extends KafkaMessage> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public String produce(T message) throws JsonProcessingException {
        message.setMessageId(UUID.randomUUID().toString());
        message.setDateTime(new Date());
        String json = objectMapper.writeValueAsString(message);
        log.info("Producing {}", message);
        kafkaTemplate.send(KafkaConfig.POC_TOPIC, json);
        return message.getMessageId();
    }
}
