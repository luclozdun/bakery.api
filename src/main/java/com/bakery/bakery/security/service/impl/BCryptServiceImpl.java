package com.bakery.bakery.security.service.impl;

import com.bakery.bakery.security.service.BCryptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptServiceImpl implements BCryptService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;    

    @Override
    public String encryptPassword(String password) {
        String encryptPassword = passwordEncoder.encode(password);
        return encryptPassword;
    }

    @Override
    public Boolean verifyPassword(String password, String encrypt) {
        Boolean valid = passwordEncoder.matches(password, encrypt);
        return valid;
    }
    
}
