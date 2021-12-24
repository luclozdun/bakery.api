package com.bakery.bakery.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Baker;
import com.bakery.bakery.security.repository.BakerRepository;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BakerServiceImpl extends CrudServiceImpl<Baker, Long> implements BakerService{

    @Autowired
    private BakerRepository repository;

    @Override
    protected GenericRepository<Baker, Long> repository() {
        return repository;
    }

    @Override
    public Optional<Baker> getBakerByUsername(String username) {
        return repository.findBakerByUsername(username);
    }

    @Override
    public Optional<Baker> getBakerByEmail(String email) {
        return repository.findBakerByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("BAKER"));
        return new User(username, password, authorities);
    }

    @Override
    public Optional<Baker> getBakerByDni(String dni) {
        return repository.findBakerByDni(dni);
    }

    @Override
    public Optional<Baker> getBakerByRuc(String ruc) {
        return repository.findBakerByRuc(ruc);
    }

    @Override
    public Optional<Baker> getBakerByNumber(String number) {
        return repository.findBakerByNumber(number);
    }
    
}
