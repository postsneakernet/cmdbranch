package com.elliothutchinson.cmdbranch.jobdef.common.generic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity> extends CrudRepository<T, Long> {

    @Override
    List<T> findAll();

    List<T> findByName(String name);
}
