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

    @KafkaListener(topics = KafkaConstants.TOPIC_PAYMENT, groupId = KafkaConstants.CONSUMER_GROUP_PAYMENT)
    public void consumePayment(ConsumerRecord<String, PaymentMessage> record) {
        log.info("*** Consumed Topic: {} | Partition: {} | Offset: {} | MessageKey: {} | Payload: {}",
                record.topic(), record.partition(), record.offset(), record.key(), record.value()
        );
    }
}
