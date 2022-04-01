package com.poc.kafka.service;

import com.poc.kafka.model.Customer;
import com.poc.kafka.repository.es.ESCustomerRepository;
import com.poc.kafka.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElasticSearchService implements SearchService {

    private final ESCustomerRepository esCustomerRepository;

    @Override
    public List<Customer> searchCustomerByName(String customerName) {
        return esCustomerRepository
                .findESCustomerByFirstNameStartingWithOrLastNameStartingWith(customerName, customerName)
                .stream()
                .map(CustomerMapper::mapToCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> searchCustomerByEmail(String customerEmail) {
        return esCustomerRepository.findESCustomerByEmailContaining(customerEmail)
                .stream().map(CustomerMapper::mapToCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> searchCustomerByPhrase(String phrase) {
        return esCustomerRepository
                .findESCustomerByFirstNameStartingWithOrLastNameStartingWithOrEmailContaining(phrase, phrase, phrase)
                .stream().map(CustomerMapper::mapToCustomer)
                .collect(Collectors.toList());
    }
}
