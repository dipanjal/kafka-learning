package com.poc.kafka.model.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentMessage extends KafkaMessage {
    private String productName;
    private String productId;
    private String walletId;
}
