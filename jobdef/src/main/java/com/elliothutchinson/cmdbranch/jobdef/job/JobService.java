package com.elliothutchinson.cmdbranch.jobdef.job;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JobService extends GenericService<Job, JobRepository> {

    public Job createJob(Job job) {
        Job createdJob = new Job();
        createdJob.setName(job.getName());
        createdJob.setCommand(job.getCommand());
        createdJob.setCommandLanguage(job.getCommandLanguage());
        createdJob.setArguments(job.getArguments());
        return repository.save(createdJob);
    }
}
