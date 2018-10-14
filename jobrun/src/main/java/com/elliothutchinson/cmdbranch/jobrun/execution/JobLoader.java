package com.elliothutchinson.cmdbranch.jobrun.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Lazy
public class JobLoader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobRunner jobRunner;

    @Async(AsyncConfig.JOB_QUEUE)
    public CompletableFuture<String> addJob(Job job) {
        logger.info("{}::adding job", job.getName());

        jobRunner.executeJob(job);

        logger.info("{}::finished adding job", job.getName());
        return CompletableFuture.completedFuture("finished");
    }
}
