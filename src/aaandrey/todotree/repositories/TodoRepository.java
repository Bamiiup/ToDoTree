package aaandrey.todotree.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aaandrey.todotree.domain.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
	@Query("SELECT todo FROM Todo todo JOIN todo.user user WHERE user.id = ?1")
	List<Todo> findByUserId(Long userId);
}
