package com.poc.kafka.controller;

import com.poc.kafka.model.Customer;
import com.poc.kafka.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search/customer/name/{name}")
    public ResponseEntity<List<Customer>> searchCustomerName(@PathVariable final String name) {
        return ResponseEntity.ok(searchService.searchCustomerByName(name));
    }

    @GetMapping("/search/customer/email/{email}")
    public ResponseEntity<List<Customer>> searchCustomerEmail(@PathVariable final String email) {
        return ResponseEntity.ok(searchService.searchCustomerByEmail(email));
    }

    @GetMapping("/search/customer/{phrase}")
    public ResponseEntity<List<Customer>> searchCustomerByPhrase(@PathVariable final String phrase) {
        return ResponseEntity.ok(searchService.searchCustomerByPhrase(phrase));
    }
}
