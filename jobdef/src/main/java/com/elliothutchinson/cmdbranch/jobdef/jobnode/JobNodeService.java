package com.elliothutchinson.cmdbranch.jobdef.jobnode;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JobNodeService extends GenericService<JobNode, JobNodeRepository> {

    public JobNode createJobNode(JobNode jobNode) {
        JobNode createdJobNode = new JobNode();
        createdJobNode.setName(jobNode.getName());
        createdJobNode.setChildJobs(jobNode.getChildJobs());
        return repository.save(createdJobNode);
    }
}
