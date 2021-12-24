package com.bakery.bakery.security.service;

import java.util.Optional;

import com.bakery.bakery.security.model.Customer;
import com.bakery.bakery.service.CrudService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerService extends CrudService<Customer, Long>{
    Optional<Customer> getByUsername(String username);
    Optional<Customer> getByEmail(String email);
    Optional<Customer> getByNumber(String number);
    UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;
}
