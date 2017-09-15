package aaandrey.todotree.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import aaandrey.todotree.domain.Tag;
import aaandrey.todotree.domain.Todo;
import aaandrey.todotree.domain.User;
import aaandrey.todotree.service.annotation.Transactional;
import aaandrey.todotree.service.domain.PlainTag;
import aaandrey.todotree.service.domain.PlainTodo;
import aaandrey.todotree.service.utils.Converter;
import aaandrey.todotree.service.utils.TransactionUtils;

public class TodoService implements ITodoService {

	private EntityManager entityManager;

	// TODO: implement this
	private void validateForRemove(EntityManager entityManager, Long userId, Long todoId) {

	}

	// TODO: implement this
	private void authorizationForRemove(EntityManager entityManager, Long userId, Long todoId) {

	}

	@Override
	@Transactional
	public PlainTodo remove(Long userId, Long todoId) {
		validateForRemove(entityManager, userId, todoId);
		authorizationForRemove(entityManager, userId, todoId);

		Todo todo = entityManager.find(Todo.class, todoId);
		PlainTodo result = Converter.toPlainTodo(todo);
		todo.removeAllTags();
		todo.removeUser();
		entityManager.remove(todo);

		return result;
	}

	@Override
	@Transactional
	public List<PlainTodo> getList(Long userId) {
		List<Todo> todoList = entityManager
				.createQuery("SELECT todo FROM Todo todo JOIN todo.user user WHERE user.id = :userId", Todo.class)
				.setParameter("userId", userId).getResultList();
		return todoList.stream().map(Converter::toPlainTodo).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PlainTodo update(Long userId, PlainTodo plainTodo) {
		validateForUpdate(entityManager, userId, plainTodo);
		authorizationForUpdate(entityManager, userId, plainTodo);

		Todo todo = entityManager.find(Todo.class, plainTodo.getId());
		todo.setComment(plainTodo.getComment());
		todo.setEndDate((Date) plainTodo.getEndDate().clone());
		todo.setImportant(plainTodo.getImportant());
		todo.setName(plainTodo.getName());
		todo.setPriority(plainTodo.getPriority());
		todo.setStartDate((Date) plainTodo.getStartDate().clone());
		todo.setWeight(plainTodo.getWeight());
		todo.setTags(findOrCreate(entityManager, plainTodo.getTags()));

		return Converter.toPlainTodo(todo);
	}

	// TODO: implement this
	private void validateForUpdate(EntityManager entityManager, Long userId, PlainTodo plainTodo) {

	}

	// TODO: implement this
	private void authorizationForUpdate(EntityManager entityManager, Long userId, PlainTodo plainTodo) {

	}

	@Override
	@Transactional
	public PlainTodo get(Long userId, Long todoId) {
		return get(entityManager, userId, todoId);
	}

	private PlainTodo get(EntityManager entityManager, Long userId, Long todoId) {
		validateForGet(entityManager, userId, todoId);
		authorizationForGet(entityManager, userId, todoId);

		Todo todo = entityManager.find(Todo.class, todoId);

		return Converter.toPlainTodo(todo);
	}

	// TODO: implement this
	private void validateForGet(EntityManager entityManager, Long userId, Long todoId) {

	}

	// TODO: implement this
	private void authorizationForGet(EntityManager entityManager, Long userId, Long todoId) {

	}

	@Override
	@Transactional
	public PlainTodo create(Long userId, PlainTodo plainTodo) {
		validateForCreate(entityManager, userId, plainTodo);

		Todo todo = create(entityManager, userId, plainTodo);

		PlainTodo result = Converter.toPlainTodo(todo);

		return result;
	}

	// TODO: implement this
	private void validateForCreate(EntityManager entityManager, Long userId, PlainTodo plainTodo) {

	}

	private Todo create(EntityManager entityManager, Long userId, PlainTodo plainTodo) {
		Todo todo = new Todo();

		todo.setComment(plainTodo.getComment());
		todo.setEndDate((Date) plainTodo.getEndDate().clone());
		todo.setStartDate((Date) plainTodo.getStartDate().clone());
		todo.setImportant(plainTodo.getImportant());
		todo.setName(plainTodo.getName());
		todo.setPriority(plainTodo.getPriority());
		todo.setWeight(plainTodo.getWeight());

		User user = entityManager.find(User.class, userId);
		todo.setUser(user);

		entityManager.persist(todo);

		findOrCreate(entityManager, plainTodo.getTags()).forEach(todo::addTag);

		return todo;
	}

	private List<Tag> findOrCreate(EntityManager entityManager, Collection<PlainTag> plainTags) {
		return plainTags.stream().map(plainTag -> {

			List<Tag> foundTags = entityManager.createQuery("SELECT tag FROM Tag tag WHERE tag.name = :name", Tag.class)
					.setParameter("name", plainTag.getName()).getResultList();

			if (foundTags.size() == 1) {
				return foundTags.get(0);
			} else {
				Tag tag = new Tag(plainTag.getName());
				entityManager.persist(tag);
				return tag;
			}
		}).collect(Collectors.toList());
	}
}
