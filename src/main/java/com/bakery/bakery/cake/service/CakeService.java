package com.bakery.bakery.cake.service;

import java.util.ArrayList;
import java.util.List;

import com.bakery.bakery.cake.model.Cake;
import com.bakery.bakery.service.CrudService;

public interface CakeService extends CrudService<Cake, Long> {
    List<Cake> getAllByBakerId(Long id);

    List<Cake> getAllByIds(ArrayList<Long> ids);
}
