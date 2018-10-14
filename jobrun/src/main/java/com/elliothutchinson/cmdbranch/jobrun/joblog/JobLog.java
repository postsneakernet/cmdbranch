package com.elliothutchinson.cmdbranch.jobrun.joblog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobLog {
    public static final String ROOT_NAME = "jobLogs";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private Long jobBatchId;
    private Long jobScheduleId;
    private String name;
    private int exitValue;
    private boolean successful;
    private String stdOut;
    private String stdErr;
    private LocalDateTime dateRun;
}
