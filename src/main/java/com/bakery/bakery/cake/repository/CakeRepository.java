package com.bakery.bakery.cake.repository;

import java.util.ArrayList;
import java.util.List;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends GenericRepository<Cake, Long> {
    List<Cake> getCakesByBakerId(Long id);

    List<Cake> findByIdIn(ArrayList<Long> ids);
}
