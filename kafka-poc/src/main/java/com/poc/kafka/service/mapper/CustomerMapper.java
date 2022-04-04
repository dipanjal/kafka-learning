package com.poc.kafka.service.mapper;

import com.poc.kafka.model.Customer;
import com.poc.kafka.repository.es.entity.ESCustomer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer mapToCustomer(ESCustomer esCustomer) {
        return Customer.builder()
                .id(esCustomer.getId())
                .firstName(esCustomer.getFirstName())
                .lastName(esCustomer.getLastName())
                .email(esCustomer.getEmail())
                .build();
    }

    public static List<Customer> mapToCustomer(List<ESCustomer> esCustomers) {
        return esCustomers.stream()
                .map(CustomerMapper::mapToCustomer)
                .collect(Collectors.toList());
    }
}
