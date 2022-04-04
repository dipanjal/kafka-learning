package com.poc.kafka.service;

import com.poc.kafka.model.Customer;

import java.io.IOException;
import java.util.List;

public interface SearchService {

    List<Customer> searchCustomerByName(final String customerName);
    List<Customer> searchCustomerByEmail(final String customerEmail);
    List<Customer> searchCustomerByPhrase(final String customerPhrase);
    List<Customer> customerGlobalSearch(String phrase) throws IOException;
}
