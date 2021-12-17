package com.bakery.bakery.cake.repository;

import com.bakery.bakery.cake.model.FillerCake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface FillerCakeRepository extends GenericRepository<FillerCake, Long> {
    
}
