package com.poc.kafka.repository.es;

import com.poc.kafka.repository.es.entity.ESCustomer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ESCustomerRepository extends ElasticsearchRepository<ESCustomer, String> {
    List<ESCustomer> findESCustomerByFirstNameStartingWithOrLastNameStartingWith(String firstName, String lastName);
    List<ESCustomer> findESCustomerByEmailContaining(String email);
    List<ESCustomer> findESCustomerByFirstNameStartingWithOrLastNameStartingWithOrEmailContaining(String firstName, String lastName, String email);

}
