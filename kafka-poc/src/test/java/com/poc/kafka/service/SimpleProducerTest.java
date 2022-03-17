package com.poc.kafka.service;


import com.poc.kafka.KafkaConstants;
import com.poc.kafka.model.message.KafkaMessage;
import com.poc.kafka.producer.GenericMessageProducer;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleProducerTest {

    @Getter
    @ToString(callSuper = true)
    private static class SimpleTestMessage extends KafkaMessage {
        private final String message;
        public SimpleTestMessage(String message) {
            this.message = message;
        }
    }

    @Autowired
    private GenericMessageProducer<String, SimpleTestMessage> stringMessageProducer;

    @Test
    public void paymentTest() throws InterruptedException {
        for(int i=0; i<3; i++) {

            String messageId = stringMessageProducer.produce(KafkaConstants.TOPIC_TEST, new SimpleTestMessage("Test Message "+i));
            Thread.sleep(500);
            Assertions.assertNotNull(messageId);
        }
    }
}
