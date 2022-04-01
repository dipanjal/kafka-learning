package com.poc.kafka.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {
    private String id;
    private String name;
    private String email;
}
