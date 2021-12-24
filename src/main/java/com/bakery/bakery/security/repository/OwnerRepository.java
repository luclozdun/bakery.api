package com.bakery.bakery.security.repository;

import java.util.Optional;

import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Owner;

import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends GenericRepository<Owner, Long>{
    Optional<Owner> getOwnerByUsername(String username);
    Optional<Owner> getOwnerByNumber(String number);
    Optional<Owner> getOwnerByEmail(String email);
}
