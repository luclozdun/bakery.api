package com.bakery.bakery.cake.repository;

import com.bakery.bakery.cake.model.TypeCake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TypeCakeRepository extends GenericRepository<TypeCake, Long>{
    
}
