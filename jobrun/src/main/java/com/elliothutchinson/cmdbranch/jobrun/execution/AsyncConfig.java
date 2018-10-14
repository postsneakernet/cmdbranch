package com.elliothutchinson.cmdbranch.jobrun.execution;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    public static final String JOB_QUEUE = "jobQueue";
    public static final String JOB_EXEC = "jobExec";

    @Bean(name = JOB_QUEUE)
    public Executor jobQueue() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("QueueThread-");
        executor.initialize();
        return executor;
    }

    @Bean(name = JOB_EXEC)
    public Executor jobExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("ExecThread-");
        executor.initialize();
        return executor;
    }
}
