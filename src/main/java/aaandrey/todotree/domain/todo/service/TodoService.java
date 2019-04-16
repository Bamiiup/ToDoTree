package aaandrey.todotree.domain.todo.service;

import aaandrey.todotree.domain.tag.service.ITagService;
import aaandrey.todotree.domain.todo.dto.PlainTodo;
import aaandrey.todotree.domain.todo.entity.Todo;
import aaandrey.todotree.domain.todo.repository.TodoRepository;
import aaandrey.todotree.domain.user.entity.User;
import aaandrey.todotree.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {
    @Autowired
    private TodoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ITodoConverter todoConverter;

    @Override
    @Transactional
    public PlainTodo remove(Long userId, Long todoId) {
        Todo todo = repository.findOne(todoId);
        PlainTodo result = todoConverter.toPlain(todo);
        repository.delete(todo);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlainTodo> getList(Long userId) {
        List<Todo> todoList = repository.findByUserId(userId);
        return todoList.stream().map(todoConverter::toPlain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PlainTodo update(Long userId, PlainTodo plainTodo) {
        Todo todo = repository.findOne(plainTodo.getId());
        todo.setComment(plainTodo.getComment());
        todo.setEndDate((Date) plainTodo.getEndDate().clone());
        todo.setImportant(plainTodo.getImportant());
        todo.setName(plainTodo.getName());
        todo.setPriority(plainTodo.getPriority());
        todo.setStartDate((Date) plainTodo.getStartDate().clone());
        todo.setWeight(plainTodo.getWeight());
        todo.setTags(tagService.findOrCreate(plainTodo.getTags()));
        todo.setChildren(plainTodo.getChildIds().stream().map(id -> repository.findOne(id)).collect(Collectors.toSet()));
        setParent(todo, plainTodo.getParentId());

        return todoConverter.toPlain(todo);
    }

    private void setParent(Todo target, Long parentId) {
        if (parentId == null) {
            target.removeParent();
        } else {
            target.setParent(repository.findOne(parentId));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PlainTodo get(Long userId, Long todoId) {
        Todo todo = repository.findOne(todoId);
        return todoConverter.toPlain(todo);
    }

    @Override
    @Transactional
    public PlainTodo create(Long userId, PlainTodo plainTodo) {
        Todo todo = new Todo();

        todo.setComment(plainTodo.getComment());
        todo.setEndDate((Date) plainTodo.getEndDate().clone());
        todo.setStartDate((Date) plainTodo.getStartDate().clone());
        todo.setImportant(plainTodo.getImportant());
        todo.setName(plainTodo.getName());
        todo.setPriority(plainTodo.getPriority());
        todo.setWeight(plainTodo.getWeight());
        setParent(todo, plainTodo.getParentId());

        User user = userRepository.findOne(userId);
        todo.setUser(user);

        repository.save(todo);

        tagService.findOrCreate(plainTodo.getTags()).forEach(todo::addTag);

        return todoConverter.toPlain(todo);
    }
}
