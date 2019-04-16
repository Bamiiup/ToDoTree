package aaandrey.todotree.domain.todo.service;

import aaandrey.todotree.domain.todo.dto.PlainTodo;

import java.util.List;

public interface ITodoService {

    PlainTodo remove(Long userId, Long todoId);

    List<PlainTodo> getList(Long userId);

    PlainTodo update(Long userId, PlainTodo plainTodo);

    PlainTodo get(Long userId, Long todoId);

    PlainTodo create(Long userId, PlainTodo plainTodo);

}