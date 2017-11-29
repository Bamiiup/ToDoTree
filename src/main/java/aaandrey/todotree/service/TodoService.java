package aaandrey.todotree.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.Todo;
import aaandrey.todotree.domain.User;
import aaandrey.todotree.repositories.TodoRepository;
import aaandrey.todotree.repositories.UserRepository;
import aaandrey.todotree.service.domain.PlainTodo;
import aaandrey.todotree.service.utils.Converter;

@Component
public class TodoService implements ITodoService {
	@Autowired
	private TodoRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ITagService tagService;

	// TODO: implement this
	private void validateForRemove(Long userId, Long todoId) {

	}

	// TODO: implement this
	private void authorizationForRemove(Long userId, Long todoId) {

	}

	@Override
	@Transactional
	public PlainTodo remove(Long userId, Long todoId) {
		validateForRemove(userId, todoId);
		authorizationForRemove(userId, todoId);

		Todo todo = repository.findOne(todoId);
		PlainTodo result = Converter.toPlain(todo);
		repository.delete(todo);

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlainTodo> getList(Long userId) {
		List<Todo> todoList = repository.findByUserId(userId);
		return todoList.stream().map(Converter::toPlain).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PlainTodo update(Long userId, PlainTodo plainTodo) {
		validateForUpdate(userId, plainTodo);
		authorizationForUpdate(userId, plainTodo);

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

		return Converter.toPlain(todo);
	}

	private void setParent(Todo target, Long parentId) {
		if (parentId == null) {
			target.removeParent();
		} else {
			target.setParent(repository.findOne(parentId));
		}
	}

	// TODO: implement this
	private void validateForUpdate(Long userId, PlainTodo plainTodo) {

	}

	// TODO: implement this
	private void authorizationForUpdate(Long userId, PlainTodo plainTodo) {

	}

	@Override
	@Transactional(readOnly = true)
	public PlainTodo get(Long userId, Long todoId) {
		validateForGet(userId, todoId);
		authorizationForGet(userId, todoId);

		Todo todo = repository.findOne(todoId);

		return Converter.toPlain(todo);
	}

	// TODO: implement this
	private void validateForGet(Long userId, Long todoId) {

	}

	// TODO: implement this
	private void authorizationForGet(Long userId, Long todoId) {

	}

	@Override
	@Transactional
	public PlainTodo create(Long userId, PlainTodo plainTodo) {
		validateForCreate(userId, plainTodo);

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

		PlainTodo result = Converter.toPlain(todo);

		return result;
	}

	// TODO: implement this
	private void validateForCreate(Long userId, PlainTodo plainTodo) {

	}
}
