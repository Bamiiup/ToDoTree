package aaandrey.todotree.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.Tag;
import aaandrey.todotree.repositories.TagCrud;
import aaandrey.todotree.service.domain.PlainTag;

@Component
public class TagService {

	@Autowired
	private TagCrud tagCrud;

	@Transactional
	public List<Tag> findOrCreate(Collection<PlainTag> plainTags) {
		return plainTags.stream().map(plainTag -> {

			List<Tag> foundTags = tagCrud.findByName(plainTag.getName());

			if (foundTags.size() == 1) {
				return foundTags.get(0);
			} else {
				Tag tag = new Tag(plainTag.getName());
				tagCrud.save(tag);
				return tag;
			}
		}).collect(Collectors.toList());
	}
}
