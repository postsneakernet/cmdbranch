package com.elliothutchinson.cmdbranch.jobdef.jobbatch;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JobBatchService extends GenericService<JobBatch, JobBatchRepository> {

    public JobBatch createJobBatch(JobBatch jobBatch) {
        JobBatch createdJobBatch = new JobBatch();
        createdJobBatch.setName(jobBatch.getName());
        createdJobBatch.setSourceJob(jobBatch.getSourceJob());
        return repository.save(createdJobBatch);
    }
}
