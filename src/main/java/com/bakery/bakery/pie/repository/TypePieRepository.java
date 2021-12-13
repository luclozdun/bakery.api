package com.bakery.bakery.pie.repository;

import com.bakery.bakery.pie.model.TypePie;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TypePieRepository extends GenericRepository<TypePie, Long>{
    
}
