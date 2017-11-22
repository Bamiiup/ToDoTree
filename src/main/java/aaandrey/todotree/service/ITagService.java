package aaandrey.todotree.service;

import java.util.Collection;
import java.util.List;

import aaandrey.todotree.domain.Tag;
import aaandrey.todotree.service.domain.PlainTag;

public interface ITagService {

	List<Tag> findOrCreate(Long userId, Collection<PlainTag> plainTags);

}