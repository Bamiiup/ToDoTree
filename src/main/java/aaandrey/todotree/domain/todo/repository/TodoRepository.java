package aaandrey.todotree.domain.todo.repository;

import aaandrey.todotree.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Query("SELECT todo FROM Todo todo JOIN todo.user user WHERE user.id = ?1")
    List<Todo> findByUserId(Long userId);
}
