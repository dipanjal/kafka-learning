package com.poc.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private String address;
}
