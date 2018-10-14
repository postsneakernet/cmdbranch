package com.elliothutchinson.cmdbranch.jobrun.runner;

import com.elliothutchinson.cmdbranch.jobrun.common.Mapper;
import com.elliothutchinson.cmdbranch.jobrun.execution.Job;
import com.elliothutchinson.cmdbranch.jobrun.execution.Job.JobBuilder;
import com.elliothutchinson.cmdbranch.jobrun.execution.JobLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@ConditionalOnProperty(prefix = "debug", name = "job.execution")
@Component
public class DebugJobExecution implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLoader jobLoader;

    @Autowired
    private Mapper mapper;

    @Override
    public void run(String... args) throws Exception {
        logger.info("DebugJobExecution::run start");

        JobBuilder jobBuilder = new JobBuilder();

        Job jobA = jobBuilder.
                jobId(1L).
                jobBatchId(1L).
                jobScheduleId(1L).
                name("jobA").
                command("import time, sys\ntime.sleep(3)\nprint(\"hello, world\")\nsys.stderr.write(\"oops\")\n").
                commandLanguage("python").
                arguments("").
                childJobs(new ArrayList<>()).
                build();

        Job jobAA = jobBuilder.
                jobId(2L).
                jobBatchId(1L).
                jobScheduleId(1L).
                name("jobAA").
                command("command jobAA with error").
                commandLanguage("error").
                arguments("").
                childJobs(new ArrayList<>()).
                build();

        Job jobAB = jobBuilder.
                jobId(3L).
                jobBatchId(1L).
                jobScheduleId(1L).
                name("jobAB").
                command("command jobAB bash").
                commandLanguage("bash").
                arguments("").
                childJobs(new ArrayList<>()).
                build();

        Job jobABA = jobBuilder.
                jobId(4L).
                jobBatchId(1L).
                jobScheduleId(1L).
                name("jobABA").
                command("import time, sys\ntime.sleep(3)\nprint(\"hello, world\")\nsys.stderr.write(\"oops\")\n").
                commandLanguage("python").
                arguments("").
                childJobs(new ArrayList<>()).
                build();

        Job jobABAA = jobBuilder.
                jobId(5L).
                jobBatchId(1L).
                jobScheduleId(1L).
                name("jobABAA").
                command("import time, sys\ntime.sleep(3)\nprint(\"hello, world\")\nsys.stderr.write(\"oops\")\n").
                commandLanguage("python").
                arguments("").
                childJobs(new ArrayList<>()).
                build();

        jobAB.addChildJob(jobABA);

        jobABA.addChildJob(jobABAA);

        jobA.addChildJob(jobAA);
        jobA.addChildJob(jobAB);

        System.out.println(mapper.convertToJson(jobA, "jobs"));

        jobLoader.addJob(jobA);

        logger.info("DebugJobExecution::run end");
    }
}
