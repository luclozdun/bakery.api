package com.bakery.bakery.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Customer;
import com.bakery.bakery.security.repository.CustomerRepository;
import com.bakery.bakery.security.service.CustomerService;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends CrudServiceImpl<Customer, Long> implements CustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    public Optional<Customer> getByUsername(String username) {
        return repository.getCustomerByUsername(username);
    }

    @Override
    public Optional<Customer> getByEmail(String email) {
        return repository.getCustomerByEmail(email);
    }

    @Override
    public Optional<Customer> getByNumber(String number) {
        return repository.getCustomerByNumber(number);
    }

    @Override
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        var customer = repository.getCustomerByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
        return new User(customer.getUsername(), customer.getPassword(), authorities);
    }

    @Override
    protected GenericRepository<Customer, Long> repository() {
        return repository;
    }
    
}
