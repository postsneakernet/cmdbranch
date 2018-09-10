package com.elliothutchinson.cmdbranch.jobdef.tag;

import com.elliothutchinson.cmdbranch.jobdef.common.generic.GenericController;
import com.elliothutchinson.cmdbranch.jobdef.common.ApiConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConfig.API_ROOT + "/tags")
public class TagController extends GenericController<Tag, TagService> {

    public TagController() {
        super(Tag.ROOT_NAME);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTag(@RequestBody Tag tag) {
        Tag createdTag = service.createTag(tag);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }
}
