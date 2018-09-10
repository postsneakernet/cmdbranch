package com.elliothutchinson.cmdbranch.jobdef.tag;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag extends GenericEntity {
    public static final String ROOT_NAME = "tags";
}
