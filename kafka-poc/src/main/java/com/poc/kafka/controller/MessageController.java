package com.poc.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.kafka.model.message.PaymentMessage;
import com.poc.kafka.model.request.PaymentRequest;
import com.poc.kafka.producer.POCProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final POCProducer<PaymentMessage> paymentMessageProducer;

    @PostMapping("/produce")
    public ResponseEntity<String> produceMessage(@RequestBody PaymentRequest request) throws JsonProcessingException {
        String messageId = paymentMessageProducer.produce(
                new PaymentMessage(request.getProductName(), request.getProductId(), request.getWalletId())
        );
        return ResponseEntity.ok(messageId);
    }
}
