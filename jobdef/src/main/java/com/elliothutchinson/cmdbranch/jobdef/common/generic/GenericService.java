package com.elliothutchinson.cmdbranch.jobdef.common.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericService<T extends GenericEntity, U extends GenericRepository<T>> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected U repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAllByName(String name) {
        return repository.findByName(name);
    }

    public T find(Long id) {
        return repository.findById(id).get();
    }
}
