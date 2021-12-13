package com.bakery.bakery.pie.repository;

import com.bakery.bakery.pie.model.FormPie;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface FormPieRepository extends GenericRepository<FormPie, Long>{
    
}
