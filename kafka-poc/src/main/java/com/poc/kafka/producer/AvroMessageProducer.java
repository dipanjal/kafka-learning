package com.poc.kafka.producer;

import com.poc.kafka.KafkaConstants;
import com.poc.kafka.model.avro.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AvroMessageProducer {

    private final KafkaTemplate<String, Payment> kafkaTemplate;

/*    public String produce(Object o) {
        return UUID.randomUUID().toString();
    }*/

    public String produce(Payment payment) {
        ProducerRecord<String, Payment> record = new ProducerRecord<>(KafkaConstants.TOPIC_TEST, UUID.randomUUID().toString(), payment);

//        Message<Payment> message = MessageBuilder.withPayload(payment).build();

        this.publishToBroker(record);
        return record.key();
    }

    private void publishToBroker(ProducerRecord<String, Payment> record) {
        kafkaTemplate.send(record)
                .addCallback(new ListenableFutureCallback<>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error(ex.getMessage(), ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, Payment> result) {
                        RecordMetadata metadata = result.getRecordMetadata();

                        log.info("### Delivered to Topic: {} | Partition: {} |  Offset {} | Timestamp {} | Payload: {}",
                                metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp(),
                                result.getProducerRecord().value()
                        );
                    }
                });
    }
}
