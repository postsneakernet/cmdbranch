package com.elliothutchinson.cmdbranch.jobrun.execution.strategy;

import java.util.ArrayList;
import java.util.List;

public class PythonExecutionStrategy implements ExecutionStrategy {

    @Override
    public String getExecutor() {
        return "python";
    }

    @Override
    public String getFilename(String path) {
        return path + "script.py";
    }

    @Override
    public List<String> getArrangedArguments(String path, String combinedArgs) {
        List<String> arguments = new ArrayList<>();
        arguments.add(getFilename(path));
        arguments.addAll(getAdditionalArguments(combinedArgs));
        return arguments;
    }
}
