package com.elliothutchinson.cmdbranch.jobdef.job;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericEntity;
import com.elliothutchinson.cmdbranch.jobdef.tag.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job extends GenericEntity {
    public static final String ROOT_NAME = "jobs";

    @NotBlank
    private String command;
    @NotBlank
    private String commandLanguage;
    private String arguments = "";
    @NotBlank
    private String description;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "job_tag", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = { "job_id", "tag_id" }))
    private Set<Tag> tags;
}
