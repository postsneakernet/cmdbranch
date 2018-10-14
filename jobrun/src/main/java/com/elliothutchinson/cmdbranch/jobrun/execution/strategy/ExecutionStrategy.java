package com.elliothutchinson.cmdbranch.jobrun.execution.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ExecutionStrategy {

    String getExecutor();

    default String getFilename(String path) {
        return path + "file.txt";
    }

    default String[] getArguments(String path, String combinedArgs) {
        List<String> arguments = new ArrayList<>();
        arguments.add(getExecutor());
        arguments.addAll(getArrangedArguments(path, combinedArgs));
        return arguments.toArray(new String[arguments.size()]);
    }

    default List<String> getAdditionalArguments(String combinedArgs) {
        return Arrays.asList(combinedArgs.split(" "));
    }

    default List<String> getArrangedArguments(String path, String combinedArgs) {
        List<String> arguments = new ArrayList<>();
        arguments.add(getFilename(path));
        arguments.addAll(getAdditionalArguments(combinedArgs));
        return arguments;
    }
}
