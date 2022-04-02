package com.poc.kafka.service;

import com.poc.kafka.model.Customer;
import com.poc.kafka.repository.es.ESCustomerRepository;
import com.poc.kafka.repository.es.entity.ESCustomer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
public class ElasticSearchServiceTests {

    @Autowired
    private ESCustomerService esCustomerService;

    @Autowired
    private ESCustomerRepository customerRepository;

    @Test
    public void findByFirstNameTest() {
        List<Customer> names = esCustomerService.searchCustomerByName("Jhon");
        Assertions.assertNotNull(names);
    }

    @Test
    public void findByFirstNamePrefixTest() {
        List<ESCustomer> customers = customerRepository.findESCustomerByFirstNameStartingWithOrLastNameStartingWith(
                "Jh", "Jh"
        );
        customers.forEach(c -> log.info(c.toString()));
        Assertions.assertNotNull(customers);
    }

    @Test
    public void findByEmailTest() {
        List<ESCustomer> customers = customerRepository.findESCustomerByEmailContaining("jp");
        customers.forEach(c -> log.info(c.toString()));
        Assertions.assertNotNull(customers);
    }

    @Test
    public void restClientTest() throws IOException {
        List<Customer> customers = esCustomerService.customerGlobalSearch("Main");
        Assertions.assertNull(customers);
    }
}
