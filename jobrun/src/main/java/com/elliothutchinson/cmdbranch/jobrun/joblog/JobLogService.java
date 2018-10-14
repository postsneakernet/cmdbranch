package com.elliothutchinson.cmdbranch.jobrun.joblog;

import com.elliothutchinson.cmdbranch.jobrun.execution.JobResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class JobLogService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLogRepository jobLogRepository;

    public List<JobLog> findAll() {
        return jobLogRepository.findAll();
    }

    public JobLog find(Long id) {
        return jobLogRepository.findById(id).get();
    }

    public JobLog createJobLog(JobResult jobResult) {
        JobLog jobLog = new JobLog();
        jobLog.setJobId(jobResult.getJob().getJobId());
        jobLog.setJobBatchId(jobResult.getJob().getJobBatchId());
        jobLog.setJobScheduleId(jobResult.getJob().getJobScheduleId());
        jobLog.setName(jobResult.getJob().getName());
        jobLog.setExitValue(jobResult.getExitValue());
        jobLog.setSuccessful(jobResult.wasSuccessful());
        jobLog.setStdOut(jobResult.getStdOut());
        jobLog.setStdErr(jobResult.getStdErr());
        jobLog.setDateRun(LocalDateTime.now());
        return jobLogRepository.save(jobLog);
    }
}
