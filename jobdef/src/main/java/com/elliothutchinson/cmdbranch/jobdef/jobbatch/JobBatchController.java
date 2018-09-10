package com.elliothutchinson.cmdbranch.jobdef.jobbatch;

import com.elliothutchinson.cmdbranch.jobdef.common.ApiConfig;
import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_ROOT + "/batches")
public class JobBatchController extends GenericController<JobBatch, JobBatchService> {

    public JobBatchController() {
        super(JobBatch.ROOT_NAME);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createJobBatch(@RequestBody JobBatch jobBatch) {
        JobBatch createdJobBatch = service.createJobBatch(jobBatch);
        return new ResponseEntity<>(createdJobBatch, HttpStatus.CREATED);
    }
}
