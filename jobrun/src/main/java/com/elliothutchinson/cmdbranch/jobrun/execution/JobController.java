package com.elliothutchinson.cmdbranch.jobrun.execution;

import com.elliothutchinson.cmdbranch.jobrun.common.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_ROOT + "/runs")
public class JobController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLoader jobLoader;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> executeJob(@RequestBody Job job) {
        logger.info("adding job");
        jobLoader.addJob(job);
        logger.info("finished adding job");
        return new ResponseEntity<>(job, HttpStatus.ACCEPTED);
    }
}
