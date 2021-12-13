package com.bakery.bakery.pie.repository;

import com.bakery.bakery.pie.model.SizePie;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface SizePieRepository extends GenericRepository<SizePie, Long>{
    
}
