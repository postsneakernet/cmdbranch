package com.elliothutchinson.cmdbranch.jobrun.execution;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobResult {
    private final int exitValue;
    private final String stdOut;
    private final String stdErr;
    private final Job job;

    public boolean wasSuccessful() {
        return exitValue == 0;
    }
}
