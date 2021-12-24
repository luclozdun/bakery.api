package com.bakery.bakery.security.service;

import java.util.Optional;

import com.bakery.bakery.security.model.Owner;
import com.bakery.bakery.service.CrudService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface OwnerService extends CrudService<Owner, Long>{
    Optional<Owner> getByUsername(String username);
    Optional<Owner> getByEmail(String email);
    Optional<Owner> getByNumber(String number);
    UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;
}
