package com.tawsif.ecommerce.services;


import com.tawsif.ecommerce.models.Customer;
import com.tawsif.ecommerce.models.CustomerRequest;
import com.tawsif.ecommerce.models.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest request) {

        if(request == null)return null;

        Customer man = new Customer();

        man.setId(request.id());
        man.setFirstName(request.firstName());
        man.setLastName(request.lastName());
        man.setEmail(request.email());
        man.setAddress(request.address());

        //System.out.println(man);

        return man;
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
