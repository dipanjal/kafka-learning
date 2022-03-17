package com.poc.kafka.service;

import com.poc.kafka.KafkaConstants;
import com.poc.kafka.model.message.PaymentMessage;
import com.poc.kafka.model.request.PaymentRequest;
import com.poc.kafka.producer.GenericMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final GenericMessageProducer<String, PaymentMessage> paymentMessageProducer;

    public String pay(PaymentRequest request) {
        return paymentMessageProducer.produce(
                KafkaConstants.TOPIC_PAYMENT,
                request.getCategory(),
                new PaymentMessage(request.getProductName(), request.getProductId(), request.getCategory(), request.getWalletId())
        );
    }
}
