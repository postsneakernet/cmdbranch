package com.elliothutchinson.cmdbranch.jobrun.joblog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobLogRepository extends CrudRepository<JobLog, Long> {

    @Override
    List<JobLog> findAll();
}
