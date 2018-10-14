package com.elliothutchinson.cmdbranch.jobrun.execution;

import com.elliothutchinson.cmdbranch.jobrun.execution.strategy.ExecutionFactory;
import com.elliothutchinson.cmdbranch.jobrun.execution.strategy.ExecutionStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.List;

@JsonDeserialize(builder = Job.JobBuilder.class)
@Builder
@Value
public class Job {
    private final Long jobId;
    private final Long jobBatchId;
    private final Long jobScheduleId;
    private final String name;
    private final String command;
    private final String commandLanguage;
    private final String arguments;
    //private final Map<String, String> environmentVariables;
    private final List<Job> childJobs;

    public void addChildJob(Job childJob) {
        childJobs.add(childJob);
    }

    @JsonIgnore
    public ExecutionStrategy getExecutionStrategy() {
        return ExecutionFactory.getExecutionStrategy(commandLanguage);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class JobBuilder {
        public JobBuilder() {}
    }
}
