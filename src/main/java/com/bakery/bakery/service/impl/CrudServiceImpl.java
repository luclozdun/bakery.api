package com.bakery.bakery.service.impl;

import java.util.List;
import java.util.Optional;

import com.bakery.bakery.repository.GenericRepository;
import com.bakery.bakery.service.CrudService;

public abstract class CrudServiceImpl<T, ID> implements CrudService<T, ID>{
    
    protected abstract GenericRepository<T, ID> repository();

    @Override
    public T create(T t) throws Exception {
        return repository().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return repository().save(t);
    }

    @Override
    public List<T> getAll() throws Exception {
        return repository().findAll();
    }

    @Override
    public Optional<T> getById(ID id) throws Exception {
        return repository().findById(id);
    }

    @Override
    public void delete(ID id) throws Exception {
        repository().deleteById(id);
    }

}
