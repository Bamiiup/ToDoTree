package aaandrey.todotree.domain.todo.service;

import aaandrey.todotree.domain.tag.service.ITagConverter;
import aaandrey.todotree.domain.todo.dto.PlainTodo;
import aaandrey.todotree.domain.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TodoConverter implements ITodoConverter {

    @Autowired
    private ITagConverter tagConverter;

    @Override
    public PlainTodo toPlain(Todo todo) {
        PlainTodo plainTodo = new PlainTodo();

        plainTodo.setComment(todo.getComment());
        plainTodo.setEndDate(todo.getEndDate());
        plainTodo.setId(todo.getId());
        plainTodo.setImportant(todo.getImportant());
        plainTodo.setName(todo.getName());
        plainTodo.setPriority(todo.getPriority());
        plainTodo.setStartDate(todo.getStartDate());
        plainTodo.setTags(todo.getTags().stream().map(tagConverter::toPlain).collect(Collectors.toSet()));
        plainTodo.setUserId(todo.getUser().getId());
        plainTodo.setWeight(todo.getWeight());
        plainTodo.setChildIds(todo.getChildren().stream().map(Todo::getId).collect(Collectors.toSet()));
        setParentId(plainTodo, todo.getParent());

        return plainTodo;
    }

    private void setParentId(PlainTodo target, Todo parent) {
        if (parent == null) {
            target.setParentId(null);
        } else {
            target.setParentId(parent.getId());
        }
    }
}
