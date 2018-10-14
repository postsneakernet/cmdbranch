package com.elliothutchinson.cmdbranch.jobrun.execution.strategy;

public class NoOpExecutionStrategy implements ExecutionStrategy {

    @Override
    public String getExecutor() {
        return "cat";
    }
}
