package aaandrey.todotree.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import aaandrey.todotree.domain.Tag;

public interface TagCrud extends CrudRepository<Tag, Long> {
	List<Tag> findByName(String name);
}
