package com.bakery.bakery.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Owner;
import com.bakery.bakery.security.repository.OwnerRepository;
import com.bakery.bakery.security.service.OwnerService;
import com.bakery.bakery.service.impl.CrudServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl extends CrudServiceImpl<Owner, Long> implements OwnerService{

    @Autowired
    private OwnerRepository repository;

    @Override
    public Optional<Owner> getByUsername(String username) {
        return repository.getOwnerByUsername(username);
    }

    @Override
    public Optional<Owner> getByEmail(String email) {
        return repository.getOwnerByEmail(email);
    }

    @Override
    public Optional<Owner> getByNumber(String number) {
        return repository.getOwnerByNumber(number);
    }

    @Override
    protected GenericRepository<Owner, Long> repository() {
        return repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("OWNER"));
        return new User(username, password, authorities);
    }
    
}
