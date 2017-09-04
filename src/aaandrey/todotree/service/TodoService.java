package aaandrey.todotree.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import aaandrey.todotree.domain.Tag;
import aaandrey.todotree.domain.Todo;
import aaandrey.todotree.domain.User;
import aaandrey.todotree.service.domain.PlainTag;
import aaandrey.todotree.service.domain.PlainTodo;
import aaandrey.todotree.service.utils.TransactionUtils;

@Component("todoService")
public class TodoService {
	@Resource(name = "transactionUtils")
	private TransactionUtils transactionUtils;

	public PlainTodo create(Long userId, PlainTodo plainTodo) {
		return transactionUtils.performInsideTransaction(entityManager -> {
			Todo todo = create(entityManager, userId, plainTodo);

			PlainTodo result = convert(todo);

			return result;
		});
	}

	private PlainTodo convert(Todo todo) {
		PlainTodo plainTodo = new PlainTodo();

		plainTodo.setComment(todo.getComment());
		plainTodo.setEndDate(todo.getEndDate());
		plainTodo.setId(todo.getId());
		plainTodo.setImportant(todo.getImportant());
		plainTodo.setName(todo.getName());
		plainTodo.setPriority(todo.getPriority());
		plainTodo.setStartDate(todo.getStartDate());
		plainTodo.setTags(todo.getTags().stream().map(this::convert).collect(Collectors.toSet()));
		plainTodo.setUserId(todo.getUser().getId());
		plainTodo.setWeight(todo.getWeight());

		return plainTodo;
	}

	private PlainTag convert(Tag tag) {
		PlainTag plainTag = new PlainTag();

		plainTag.setId(tag.getId());
		plainTag.setName(tag.getName());
		plainTag.setTodoIds(tag.getTodoList().stream().map(todo -> todo.getId()).collect(Collectors.toSet()));

		return plainTag;
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
