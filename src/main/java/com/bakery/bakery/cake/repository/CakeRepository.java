package com.bakery.bakery.cake.repository;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends GenericRepository<Cake, Long>{
    
}
