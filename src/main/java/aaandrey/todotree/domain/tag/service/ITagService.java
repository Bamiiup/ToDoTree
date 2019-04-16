package aaandrey.todotree.domain.tag.service;

import aaandrey.todotree.domain.tag.dto.PlainTag;
import aaandrey.todotree.domain.tag.entity.Tag;

import java.util.Collection;
import java.util.List;

public interface ITagService {

    List<Tag> findOrCreate(Collection<PlainTag> plainTags);

}