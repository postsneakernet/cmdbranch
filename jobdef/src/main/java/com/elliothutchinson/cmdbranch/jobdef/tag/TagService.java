package com.elliothutchinson.cmdbranch.jobdef.tag;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TagService extends GenericService<Tag, TagRepository> {

    public Tag createTag(Tag tag) {
        Tag createdTag = new Tag();
        createdTag.setName(tag.getName());
        return repository.save(createdTag);
    }
}
