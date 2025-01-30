package com.tawsif.ecommerce.services;

import com.netflix.discovery.converters.Auto;
import com.tawsif.ecommerce.exceptions.CustomerNotFoundException;
import com.tawsif.ecommerce.models.Customer;
import com.tawsif.ecommerce.models.CustomerRequest;
import com.tawsif.ecommerce.models.CustomerResponse;
import com.tawsif.ecommerce.repositories.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public String createCustomer(@Valid CustomerRequest request) {
        //System.out.println(request.address());
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(()->new CustomerNotFoundException("Customer Not Found with Id: "+request.id()));

        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName()))customer.setFirstName(request.firstName());
        if(StringUtils.isNotBlank(request.lastName()))customer.setLastName(request.lastName());
        if(StringUtils.isNotBlank(request.email()))customer.setEmail(request.email());
        if(request.address() != null)customer.setAddress(request.address());
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found with Id: "+customerId));

    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
