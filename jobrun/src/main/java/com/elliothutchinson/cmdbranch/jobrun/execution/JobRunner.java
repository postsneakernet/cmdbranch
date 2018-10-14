package com.elliothutchinson.cmdbranch.jobrun.execution;

import com.elliothutchinson.cmdbranch.jobrun.joblog.JobLog;
import com.elliothutchinson.cmdbranch.jobrun.joblog.JobLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class JobRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLogService jobLogService;
    @Autowired
    @Lazy
    private JobLoader jobLoader;
    @Value("${debug.job.simulation}")
    private boolean jobSimulation;

    @Async(AsyncConfig.JOB_EXEC)
    public CompletableFuture<JobLog> executeJob(Job job) {
        logger.info("{}::executing job", job.getName());

        simulateExectutionTime();

        JobResult jobResult = JobManager.execute(job);
        JobLog jobLog = jobLogService.createJobLog(jobResult);

        if (jobResult.wasSuccessful()) {
            logger.info("{}::adding child jobs", job.getName());
            job.getChildJobs().stream().forEach(cj -> jobLoader.addJob(cj));
        }

        logger.info("{}::finsihed executing job", job.getName());
        return CompletableFuture.completedFuture(jobLog);
    }

    private void simulateExectutionTime() {
        if (jobSimulation) {
            try {
                logger.info("sleeping thread to simulate job execution time");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
