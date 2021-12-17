package com.bakery.bakery.cake.repository;

import com.bakery.bakery.cake.model.SizeCake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface SizeCakeRepository extends GenericRepository<SizeCake, Long>{
       
}
