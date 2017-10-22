package aaandrey.todotree.service;

import java.util.List;

import aaandrey.todotree.service.domain.PlainRepresentation;

public interface IRepresentationService {
	PlainRepresentation create(Long userId, PlainRepresentation representation);

	List<PlainRepresentation> getList(Long userId);

	PlainRepresentation update(Long userId, PlainRepresentation representation);

	PlainRepresentation remove(Long userId, Long id);

	PlainRepresentation get(Long userId, Long id);
}
