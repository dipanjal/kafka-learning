package com.poc.kafka.consumer;

import com.poc.kafka.KafkaConstants;
import com.poc.kafka.model.message.PaymentMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class POCConsumer {

    @KafkaListener(topics = KafkaConstants.TOPIC_POC, groupId = KafkaConstants.CONSUMER_GROUP_POC)
    public void consumePayment(ConsumerRecord<String, PaymentMessage> record) {
        log.info("Topic: {}", record.topic());
        log.info("Partition: {}", record.partition());
        log.info("Offset: {}", record.offset());
        log.info("Headers: {}", record.headers());
        log.info("Message Key: {}", record.key());
        log.info("Message Consumed: {}", record.value());
    }
}
