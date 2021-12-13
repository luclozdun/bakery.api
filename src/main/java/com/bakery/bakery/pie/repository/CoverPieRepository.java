package com.bakery.bakery.pie.repository;

import com.bakery.bakery.pie.model.CoverPie;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CoverPieRepository extends GenericRepository<CoverPie, Long>{
    
}
