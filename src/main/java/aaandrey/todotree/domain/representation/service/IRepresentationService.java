package aaandrey.todotree.domain.representation.service;

import aaandrey.todotree.domain.representation.dto.PlainRepresentation;

import java.util.List;

public interface IRepresentationService {
    PlainRepresentation create(Long userId, PlainRepresentation representation);

    List<PlainRepresentation> getList(Long userId);

    PlainRepresentation update(Long userId, PlainRepresentation representation);

    PlainRepresentation remove(Long userId, Long id);

    PlainRepresentation get(Long userId, Long id);
}
