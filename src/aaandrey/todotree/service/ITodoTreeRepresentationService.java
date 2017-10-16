package aaandrey.todotree.service;

import java.util.List;

import aaandrey.todotree.service.domain.PlainTodoTreeRepresentation;

public interface ITodoTreeRepresentationService {
	PlainTodoTreeRepresentation create(Long userId, PlainTodoTreeRepresentation todoTreeRepresentation);

	List<PlainTodoTreeRepresentation> getList(Long userId);

	PlainTodoTreeRepresentation update(Long userId, PlainTodoTreeRepresentation todoTreeRepresentation);

	PlainTodoTreeRepresentation remove(Long userId, Long id);
}
