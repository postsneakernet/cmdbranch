package com.elliothutchinson.cmdbranch.jobrun.joblog;

import com.elliothutchinson.cmdbranch.jobrun.common.ApiConfig;
import com.elliothutchinson.cmdbranch.jobrun.common.Mapper;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_ROOT + "/logs")
public class JobLogController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLogService jobLogService;
    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobLogs() throws JsonProcessingException {
        List<JobLog> jobLogs = jobLogService.findAll();
        String result = mapper.convertToJson(jobLogs, JobLog.ROOT_NAME);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JobLog getJobLog(@PathVariable Long id) {
        return jobLogService.find(id);
    }
}
