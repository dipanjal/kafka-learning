package com.poc.kafka.producer;

import com.poc.kafka.model.message.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericMessageProducer<K, V extends KafkaMessage> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public String produce(String topic, K key, V payload) {
        payload.setMessageId(UUID.randomUUID().toString());
        payload.setDateTime(new Date());
        log.info("Producing {}", payload);

        ProducerRecord<K, V> record = new ProducerRecord<>(topic, key, payload);
//        Message<T> message = MessageBuilder.withPayload(payload).build();

        kafkaTemplate.send(record)
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error(ex.getMessage(), ex);
                    }

                    @Override
                    public void onSuccess(SendResult<K, V> result) {
                        RecordMetadata metadata = result.getRecordMetadata();
                        log.info("Payload: {} ", result.getProducerRecord().value());
                        log.info("Delivered to Topic: {} | Partition: {} |  Offset {}", metadata.topic(), metadata.partition(), metadata.offset());
                    }
                });
        return payload.getMessageId();
    }
}
