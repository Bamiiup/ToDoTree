package aaandrey.todotree.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aaandrey.todotree.domain.TodoTreeRepresentation;

public interface TodoTreeRepresentationCrud extends CrudRepository<TodoTreeRepresentation, Long> {

	@Query("SELECT representation FROM TodoTreeRepresentation representation"
			+ " JOIN representation.user user WHERE user.id = ?1")
	List<TodoTreeRepresentation> findByUserId(Long userId);
}
