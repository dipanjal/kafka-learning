package com.poc.kafka.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFieldConstant {

    public static final String CUSTOMER_GLOBAL_SEARCH_FIELDS = "first_name, last_name, email, address";

    public static List<String> getFieldsAsList(String fields) {
        return Arrays.stream(fields.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}