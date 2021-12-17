package com.bakery.bakery.cake.repository;

import com.bakery.bakery.cake.model.TasteCake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TasteCakeRepository extends GenericRepository<TasteCake, Long>{
    
}
