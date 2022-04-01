package com.poc.kafka.repository.es.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Builder
@ToString
@Document(indexName = "mysql0.inventory.customers")
public class ESCustomer {

    @Id
    private String id;
    @Field(value = "first_name")
    private String firstName;
    @Field(value = "last_name")
    private String lastName;
    private String email;

    public ESCustomer() {}

    public ESCustomer(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
