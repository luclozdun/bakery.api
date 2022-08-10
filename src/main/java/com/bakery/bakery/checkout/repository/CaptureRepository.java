package com.bakery.bakery.checkout.repository;

import com.bakery.bakery.checkout.model.Capture;
import com.bakery.bakery.repository.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CaptureRepository extends GenericRepository<Capture, String> {

}
