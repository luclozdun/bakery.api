package com.bakery.bakery.cake.repository;

import com.bakery.bakery.cake.model.CoverCake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CoverCakeRepository extends GenericRepository<CoverCake, Long>{
    
}
