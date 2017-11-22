package aaandrey.todotree.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.Todo;
import aaandrey.todotree.domain.User;
import aaandrey.todotree.repositories.TagRepository;
import aaandrey.todotree.repositories.TodoRepository;
import aaandrey.todotree.repositories.UserRepository;
import aaandrey.todotree.service.ServiceAuthorizationException.ServiceOperation;
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

	private void validateForRemove(Long userId, Long todoId) {
		if (repository.findOne(todoId) == null) {
			throw ServiceValidationException.entityNotExist(Todo.class, todoId);
		}
	}

	private void authorizationForRemove(Long userId, Long todoId) {
		Todo todo = repository.findOne(todoId);
		if (!todo.getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Todo.class, todoId, ServiceOperation.remove);
		}
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
		todo.setTags(tagService.findOrCreate(userId, plainTodo.getTags()));
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

	private void validateForUpdate(Long userId, PlainTodo todo) {
		if (repository.findOne(todo.getId()) == null) {
			throw ServiceValidationException.entityNotExist(Todo.class, todo.getId());
		}

		todo.getChildIds().stream().filter(childId -> childId.equals(todo.getId())).findFirst().ifPresent(id -> {
			throw new ServiceValidationException("Todo has child with id that it has: " + id);
		});

		if (Objects.equals(todo.getId(), todo.getParentId())) {
			throw new ServiceValidationException("Todo has parent with id that it has: " + todo.getId());
		}

		validateForUpdateOrCreate(userId, todo);
	}

	private void validateForUpdateOrCreate(Long userId, PlainTodo todo) {
		todo.getChildIds().forEach(childId -> {
			if (repository.findOne(childId) == null) {
				throw ServiceValidationException.entityNotExist(Todo.class, childId);
			}
		});

		if (todo.getEndDate().before(todo.getStartDate())) {
			throw ServiceValidationException.endBeforeStart(Todo.class, "startDate", "endDate", todo.getId());
		}

		if (todo.getName() == null || todo.getName().isEmpty()) {
			throw ServiceValidationException.fieldIsNullOrEmpty(Todo.class, "name", todo.getId());
		}

		if (repository.findOne(todo.getParentId()) == null) {
			throw ServiceValidationException.entityNotExist(Todo.class, todo.getParentId());
		}
	}

	private void authorizationForUpdate(Long userId, PlainTodo plainTodo) {
		authorizationForUpdateOrCreate(userId, plainTodo, ServiceOperation.update);
	}

	private void authorizationForUpdateOrCreate(Long userId, PlainTodo plainTodo, ServiceOperation serviceOperation) {
		Todo todo = repository.findOne(plainTodo.getId());
		if (!todo.getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Todo.class, plainTodo.getId(), serviceOperation);
		}

		if (!repository.findOne(plainTodo.getParentId()).getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Todo.class, plainTodo.getParentId(), ServiceOperation.get);
		}

		plainTodo.getChildIds().forEach(childId -> {
			if (!repository.findOne(childId).getUser().getId().equals(userId)) {
				throw ServiceAuthorizationException.entityAccessDenied(Todo.class, childId, ServiceOperation.get);
			}
		});
	}

	@Override
	@Transactional(readOnly = true)
	public PlainTodo get(Long userId, Long todoId) {
		authorizationForGet(userId, todoId);

		Todo todo = repository.findOne(todoId);

		return Converter.toPlain(todo);
	}

	private void authorizationForGet(Long userId, Long todoId) {
		Todo todo = repository.findOne(todoId);
		if (todo == null) {
			return;
		}

		if (!todo.getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Todo.class, todoId, ServiceOperation.get);
		}
	}

	@Override
	@Transactional
	public PlainTodo create(Long userId, PlainTodo plainTodo) {
		validateForCreate(userId, plainTodo);
		authorizationForCreate(userId, plainTodo);

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

		tagService.findOrCreate(userId, plainTodo.getTags()).forEach(todo::addTag);

		PlainTodo result = Converter.toPlain(todo);

		return result;
	}

	private void validateForCreate(Long userId, PlainTodo todo) {
		validateForUpdateOrCreate(userId, todo);
	}

	private void authorizationForCreate(Long userId, PlainTodo todo) {
		authorizationForUpdateOrCreate(userId, todo, ServiceOperation.create);
	}
}
