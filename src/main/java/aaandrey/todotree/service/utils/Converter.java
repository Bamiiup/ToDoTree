package aaandrey.todotree.service.utils;

import java.util.stream.Collectors;

import aaandrey.todotree.domain.Tag;
import aaandrey.todotree.domain.Todo;
import aaandrey.todotree.domain.Representation;
import aaandrey.todotree.domain.SortRule;
import aaandrey.todotree.service.domain.PlainTag;
import aaandrey.todotree.service.domain.PlainTodo;
import aaandrey.todotree.service.domain.PlainRepresentation;

public class Converter {
	public static PlainTodo toPlain(Todo todo) {
		PlainTodo plainTodo = new PlainTodo();

		plainTodo.setComment(todo.getComment());
		plainTodo.setEndDate(todo.getEndDate());
		plainTodo.setId(todo.getId());
		plainTodo.setImportant(todo.getImportant());
		plainTodo.setName(todo.getName());
		plainTodo.setPriority(todo.getPriority());
		plainTodo.setStartDate(todo.getStartDate());
		plainTodo.setTags(todo.getTags().stream().map(Converter::toPlain).collect(Collectors.toSet()));
		plainTodo.setUserId(todo.getUser().getId());
		plainTodo.setWeight(todo.getWeight());
		plainTodo.setChildIds(todo.getChildren().stream().map(child -> child.getId()).collect(Collectors.toSet()));
		setParentId(plainTodo, todo.getParent());

		return plainTodo;
	}

	private static void setParentId(PlainTodo target, Todo parent) {
		if (parent == null) {
			target.setParentId(null);
		} else {
			target.setParentId(parent.getId());
		}
	}

	public static PlainTag toPlain(Tag tag) {
		PlainTag plainTag = new PlainTag();

		plainTag.setId(tag.getId());
		plainTag.setName(tag.getName());
		plainTag.setTodoIds(tag.getTodoList().stream().map(todo -> todo.getId()).collect(Collectors.toSet()));

		return plainTag;
	}

	public static PlainRepresentation toPlain(Representation representation) {
		PlainRepresentation result = new PlainRepresentation();

		result.setBottomPriority(representation.getBottomPriority());
		result.setBottomWeight(representation.getBottomWeight());
		result.setDayAmountAfterToday(representation.getDayAmountAfterToday());
		result.setId(representation.getId());
		result.setName(representation.getName());
		result.setIsImportant(representation.getIsImportant());
		result.setTags(representation.getTags().stream().map(Converter::toPlain).collect(Collectors.toSet()));
		result.setTopPriority(representation.getTopPriority());
		result.setTopWeight(representation.getTopWeight());
		result.setUserId(representation.getUser().getId());
		result.setSortRules(representation.getSortRules().stream().map(Converter::copy).collect(Collectors.toSet()));

		return result;
	}

	public static SortRule copy(SortRule sortRule) {
		SortRule result = new SortRule();
		result.setFieldName(sortRule.getFieldName());
		result.setIsDirect(sortRule.getIsDirect());
		result.setOrder(sortRule.getOrder());

		return result;
	}

}
