package com.bakery.bakery.security.service;

import java.util.Optional;

import com.bakery.bakery.security.model.Baker;
import com.bakery.bakery.service.CrudService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface BakerService extends CrudService<Baker, Long>{
    Optional<Baker> getBakerByUsername(String username);
    Optional<Baker> getBakerByEmail(String email);
    Optional<Baker> getBakerByDni(String dni);
    Optional<Baker> getBakerByRuc(String ruc);
    Optional<Baker> getBakerByNumber(String number);
    UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;
} 
