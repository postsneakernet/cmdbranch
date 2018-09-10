package com.elliothutchinson.cmdbranch.jobdef.jobbatch;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericEntity;
import com.elliothutchinson.cmdbranch.jobdef.jobnode.JobNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JobBatch extends GenericEntity {
    public static final String ROOT_NAME = "jobBatches";

    @OneToOne(cascade = CascadeType.PERSIST)
    private JobNode sourceJob;
}
