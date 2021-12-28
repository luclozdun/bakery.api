package com.bakery.bakery.security.repository;

import java.util.Optional;

import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Customer;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long>{
    Optional<Customer> getCustomerByUsername(String username);
    Optional<Customer> getCustomerByEmail(String email);
    Optional<Customer> getCustomerByNumber(String number);
}
