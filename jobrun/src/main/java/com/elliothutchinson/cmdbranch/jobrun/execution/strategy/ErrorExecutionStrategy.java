package com.elliothutchinson.cmdbranch.jobrun.execution.strategy;

import java.util.ArrayList;
import java.util.List;

public class ErrorExecutionStrategy implements ExecutionStrategy {

    @Override
    public String getExecutor() {
        return "ls";
    }

    @Override
    public List<String> getArrangedArguments(String path, String combinedArgs) {
        List<String> arguments = new ArrayList<>();
        arguments.add("-al");
        arguments.addAll(getAdditionalArguments(combinedArgs));
        arguments.add(getFilename(path));
        return arguments;
    }
}
