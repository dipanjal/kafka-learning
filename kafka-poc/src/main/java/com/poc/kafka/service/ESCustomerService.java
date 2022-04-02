package com.poc.kafka.service;

import com.poc.kafka.config.ElasticSearchConfig;
import com.poc.kafka.model.Customer;
import com.poc.kafka.repository.es.ESCustomerRepository;
import com.poc.kafka.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ESCustomerService implements SearchService {

    private final ESCustomerRepository esCustomerRepository;
    private final RestHighLevelClient client;

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

    @Override
    public List<Customer> customerGlobalSearch(String phrase) throws IOException {
        SearchRequest request = this.buildSearchRequest(ElasticSearchConfig.INDEX);
        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .should(QueryBuilders.matchPhrasePrefixQuery("first_name", phrase))
                .should(QueryBuilders.matchPhrasePrefixQuery("last_name", phrase))
                .should(QueryBuilders.matchPhrasePrefixQuery("email", phrase))
                .should(QueryBuilders.matchPhrasePrefixQuery("address", phrase));
        request.source(
                new SearchSourceBuilder().query(queryBuilder)
        );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        ObjectMapper mapper = new ObjectMapper();

        List<Customer> dataMap = Arrays.stream(response.getHits().getHits())
                .map(SearchHit::getSourceAsMap)
                .map(customerMap -> mapper.convertValue(customerMap, Customer.class))
                .collect(Collectors.toList());
        return dataMap;
    }

    private SearchRequest buildSearchRequest(final String index) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);

        return searchRequest;
    }

    /*private List<Customer> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Customer> profileDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit){
            profileDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), ProfileDocument.class));
        }

        return profileDocuments;
    }*/
}
