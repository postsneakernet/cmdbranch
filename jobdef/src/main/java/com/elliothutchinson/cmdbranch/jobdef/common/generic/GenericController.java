package com.elliothutchinson.cmdbranch.jobdef.common.generic;

import com.elliothutchinson.cmdbranch.jobdef.common.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public abstract class GenericController<T extends GenericEntity, U extends GenericService<T, ? extends GenericRepository<T>>> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected U service;
    @Autowired
    protected Mapper mapper;
    protected final String rootName;

    public GenericController(String rootName) {
        this.rootName = rootName;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEntities() throws JsonProcessingException {
        List<T> entities = service.findAll();
        String result = mapper.convertToJson(entities, rootName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEntitiesWithName(@PathVariable String name) throws JsonProcessingException {
        List<T> entities = service.findAllByName(name);
        String result = mapper.convertToJson(entities, rootName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T getEntity(@PathVariable Long id) {
        return service.find(id);
    }
}
