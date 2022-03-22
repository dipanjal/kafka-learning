package com.poc.kafka.service;

import com.poc.kafka.model.avro.Payment;
import com.poc.kafka.producer.AvroMessageProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AvroMessageProducerTests {

    @Autowired
    private AvroMessageProducer avroProducer;

    @Test
    public void testAvroPublisher() {
        Payment payment = Payment.newBuilder()
                .setProductId(1L)
                .setProductName("Football")
                .setCategory("Khela-Dhula")
                .setWalletId("ABC-123-XYZ")
                .build();
        String key = avroProducer.produce(payment);
        Assertions.assertNotNull(key);
    }
}
