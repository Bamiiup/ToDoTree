package aaandrey.todotree.service.utils;

import java.util.stream.Collectors;

import aaandrey.todotree.domain.Tag;
import aaandrey.todotree.domain.Todo;
import aaandrey.todotree.service.domain.PlainTag;
import aaandrey.todotree.service.domain.PlainTodo;

public class Converter {
	public static PlainTodo toPlainTodo(Todo todo) {
		PlainTodo plainTodo = new PlainTodo();

		plainTodo.setComment(todo.getComment());
		plainTodo.setEndDate(todo.getEndDate());
		plainTodo.setId(todo.getId());
		plainTodo.setImportant(todo.getImportant());
		plainTodo.setName(todo.getName());
		plainTodo.setPriority(todo.getPriority());
		plainTodo.setStartDate(todo.getStartDate());
		plainTodo.setTags(todo.getTags().stream().map(Converter::toPlainTag).collect(Collectors.toSet()));
		plainTodo.setUserId(todo.getUser().getId());
		plainTodo.setWeight(todo.getWeight());

		return plainTodo;
	}

	public static PlainTag toPlainTag(Tag tag) {
		PlainTag plainTag = new PlainTag();

		plainTag.setId(tag.getId());
		plainTag.setName(tag.getName());
		plainTag.setTodoIds(tag.getTodoList().stream().map(todo -> todo.getId()).collect(Collectors.toSet()));

		return plainTag;
	}
}