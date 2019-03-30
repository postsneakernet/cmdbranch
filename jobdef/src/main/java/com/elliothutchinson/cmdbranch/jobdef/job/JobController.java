package com.elliothutchinson.cmdbranch.jobdef.job;

import com.elliothutchinson.cmdbranch.jobdef.common.ApiConfig;
import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_ROOT + "/jobs")
public class JobController extends GenericController<Job, JobService> {

    public JobController() {
        super(Job.ROOT_NAME);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        Job createdJob = service.createJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }
}
