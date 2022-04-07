package com.poc.kafka.model.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class PaymentMessage extends KafkaMessage {
    private String productName;
    private long productId;
    private String category;
    private String walletId;
}
