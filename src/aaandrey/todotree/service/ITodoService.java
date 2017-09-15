package aaandrey.todotree.service;

import java.util.List;

import aaandrey.todotree.service.domain.PlainTodo;

public interface ITodoService {

	PlainTodo remove(Long userId, Long todoId);

	List<PlainTodo> getList(Long userId);

	PlainTodo update(Long userId, PlainTodo plainTodo);

	PlainTodo get(Long userId, Long todoId);

	PlainTodo create(Long userId, PlainTodo plainTodo);

}