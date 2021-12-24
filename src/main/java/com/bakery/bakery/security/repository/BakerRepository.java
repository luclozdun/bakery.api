package com.bakery.bakery.security.repository;

import java.util.Optional;

import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.security.model.Baker;

import org.springframework.stereotype.Repository;

@Repository
public interface BakerRepository extends GenericRepository<Baker, Long>{
    Optional<Baker> findBakerByUsername(String username);
    Optional<Baker> findBakerByEmail(String email);
    Optional<Baker> findBakerByDni(String dni);
    Optional<Baker> findBakerByRuc(String ruc);
    Optional<Baker> findBakerByNumber(String number);
}
