package com.poc.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMessage extends KafkaMessage {
    private String productName;
    private String productId;
    private String walletId;
}
