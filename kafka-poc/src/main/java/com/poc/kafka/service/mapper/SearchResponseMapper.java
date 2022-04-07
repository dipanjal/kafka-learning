package com.poc.kafka.service.mapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResponseMapper {

    public static <T> List<T> mapSearchResponse(SearchResponse response, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(response.getHits().getHits())
                .map(SearchHit::getSourceAsMap)
                .map(customerMap -> mapper.convertValue(customerMap, tClass))
                .collect(Collectors.toList());
    }
}
