package com.elliothutchinson.cmdbranch.jobdef.common;

import com.elliothutchinson.cmdbranch.jobdef.jobbatch.JobBatch;
import com.elliothutchinson.cmdbranch.jobdef.job.Job;
import com.elliothutchinson.cmdbranch.jobdef.jobnode.JobNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Endpoint {
    private Map<String, String> endpoints = new HashMap<>();

    public Endpoint(String host) {
        String scheme = "http://";
        String baseUrl = String.format("%s%s/%s", scheme, host, ApiConfig.API_ROOT);
        endpoints.put(Job.ROOT_NAME, String.format("%s/%s", baseUrl, "jobs"));
        endpoints.put(JobNode.ROOT_NAME, String.format("%s/%s", baseUrl, "jobNodes"));
        endpoints.put(JobBatch.ROOT_NAME, String.format("%s/%s", baseUrl, "jobBatches"));
    }
}
