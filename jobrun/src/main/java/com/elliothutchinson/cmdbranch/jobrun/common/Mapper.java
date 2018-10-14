package com.elliothutchinson.cmdbranch.jobrun.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    @Autowired
    private ObjectMapper mapper;

    public String convertToJson(Object entity, String rootName) throws JsonProcessingException {
        ObjectWriter writer = mapper.writer().withRootName(rootName);
        return writer.writeValueAsString(entity);
    }
}
