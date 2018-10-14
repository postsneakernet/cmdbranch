package com.elliothutchinson.cmdbranch.jobrun.execution.strategy;

import java.util.HashMap;
import java.util.Map;

public class ExecutionFactory {
    private static final ExecutionStrategy defaultStrategy = new NoOpExecutionStrategy();
    private static final Map<String, ExecutionStrategy> strategyLookup = new HashMap<>();
    static {
        strategyLookup.put("python", new PythonExecutionStrategy());
        strategyLookup.put("error", new ErrorExecutionStrategy());
    }

    public static ExecutionStrategy getExecutionStrategy(String commandLanguage) {
        return strategyLookup.getOrDefault(commandLanguage, defaultStrategy);
    }
}
