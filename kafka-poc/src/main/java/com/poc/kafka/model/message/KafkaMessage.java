package com.poc.kafka.model.message;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KafkaMessage implements Serializable {
    private String messageId;
    private Date dateTime;
}
