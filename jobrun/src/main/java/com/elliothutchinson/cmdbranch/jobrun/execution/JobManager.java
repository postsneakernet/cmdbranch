package com.elliothutchinson.cmdbranch.jobrun.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class JobManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Job job;

    private JobManager(Job job) {
        this.job = job;
    }

    private void saveCommandAsFile() {
        String parentPath = getParentPath();
        ensureDirectoryExists(parentPath);
        String filePath = job.getExecutionStrategy().getFilename(parentPath);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] bytes = getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getParentPath() {
        String trimmedJobName = getTrimmedName();
        return String.format("%s/scripts/batch_%s/schedule_%s/job_%s_%s/",
                System.getProperty("user.dir"),
                job.getJobBatchId(),
                job.getJobScheduleId(),
                job.getJobId(),
                trimmedJobName);
    }

    private String getTrimmedName() {
        return job.getName().replaceAll("\\s", "");
    }

    private void ensureDirectoryExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private byte[] getBytes() {
        return job.getCommand().getBytes();
    }

    private JobResult execute() {
        String stdOut = "n/a";
        String stdErr = "n/a";
        int exitValue = 0;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(job.getExecutionStrategy().getArguments(getParentPath(), job.getArguments()));
            Process process = processBuilder.start();
            stdOut = getOutput(process.getInputStream(), System.out);
            stdErr = getOutput(process.getErrorStream(), System.err);
            exitValue = process.exitValue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JobResult(exitValue, stdOut, stdErr, job);
    }

    private String getOutput(InputStream inputStream, PrintStream printStream) throws IOException {
        StringBuilder output = new StringBuilder();
        String line = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = bufferedReader.readLine()) != null) {
                logger.info(line);
                printStream.printf("%s%n", line);
                output.append(line + "\n");
            }
        }
        return output.toString();
    }

    public static JobResult execute(Job job) {
        JobManager jobManager = new JobManager(job);
        jobManager.saveCommandAsFile();
        return jobManager.execute();
    }
}
