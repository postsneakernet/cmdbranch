package com.elliothutchinson.cmdbranch.jobdef.jobnode;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericController;
import com.elliothutchinson.cmdbranch.jobdef.common.ApiConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_ROOT + "/nodes")
public class JobNodeController extends GenericController<JobNode, JobNodeService> {

    public JobNodeController() {
        super(JobNode.ROOT_NAME);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createJobNode(@RequestBody JobNode jobNode) {
        JobNode createdJobNode = service.createJobNode(jobNode);
        return new ResponseEntity<>(createdJobNode, HttpStatus.CREATED);
    }
}
