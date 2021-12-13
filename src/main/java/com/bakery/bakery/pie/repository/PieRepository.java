package com.bakery.bakery.pie.repository;

import com.bakery.bakery.pie.model.Pie;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PieRepository extends GenericRepository<Pie, Long>{
    
}
