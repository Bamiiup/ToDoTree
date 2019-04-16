package aaandrey.todotree.domain.todo.service;

import aaandrey.todotree.domain.todo.dto.PlainTodo;
import aaandrey.todotree.domain.todo.entity.Todo;

public interface ITodoConverter {
    PlainTodo toPlain(Todo todo);
}
