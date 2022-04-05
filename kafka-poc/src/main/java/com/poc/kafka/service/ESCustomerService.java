package com.poc.kafka.service;

import com.poc.kafka.config.ElasticSearchConfig;
import com.poc.kafka.model.Customer;
import com.poc.kafka.repository.es.ESCustomerRepository;
import com.poc.kafka.service.mapper.CustomerMapper;
import com.poc.kafka.service.mapper.SearchResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        QueryBuilder queryBuilder1 = this.buildDynamicQuery(
                phrase,
                SearchFieldConstant.getFieldsAsList(SearchFieldConstant.CUSTOMER_GLOBAL_SEARCH_FIELDS)
        );

        SearchRequest request = new SearchRequest(ElasticSearchConfig.INDEX)
                        .source(new SearchSourceBuilder().query(queryBuilder1));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        return SearchResponseMapper.mapSearchResponse(response, Customer.class);
    }

    private QueryBuilder buildDynamicQuery(String phrase, List<String> fields) {
        log.info("Building dynamic query");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        for(String field : fields) {
            queryBuilder.should(QueryBuilders.matchPhrasePrefixQuery(field, phrase));
            log.info("{} : {}", field, phrase);
        }
        return queryBuilder;
    }
}
