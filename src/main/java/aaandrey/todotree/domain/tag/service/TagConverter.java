package aaandrey.todotree.domain.tag.service;

import aaandrey.todotree.domain.tag.dto.PlainTag;
import aaandrey.todotree.domain.tag.entity.Tag;
import aaandrey.todotree.domain.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TagConverter implements ITagConverter {
    @Override
    public PlainTag toPlain(Tag tag) {
        PlainTag plainTag = new PlainTag();

        plainTag.setId(tag.getId());
        plainTag.setName(tag.getName());
        plainTag.setTodoIds(tag.getTodoList().stream().map(Todo::getId).collect(Collectors.toSet()));

        return plainTag;
    }
}
