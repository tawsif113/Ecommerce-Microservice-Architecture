package com.tawsif.ecommerce.repositories;

import com.tawsif.ecommerce.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
