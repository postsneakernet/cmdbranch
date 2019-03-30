package com.elliothutchinson.cmdbranch.jobdef.jobnode;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericEntity;
import com.elliothutchinson.cmdbranch.jobdef.job.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobNode extends GenericEntity {
    public static final String ROOT_NAME = "jobNodes";

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Job job;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<JobNode> childJobs;
}
