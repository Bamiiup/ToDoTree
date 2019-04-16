package aaandrey.todotree.domain.representation.service;

import aaandrey.todotree.domain.representation.dto.PlainRepresentation;
import aaandrey.todotree.domain.representation.entity.Representation;

public interface IRepresentationConverter {
    PlainRepresentation toPlain(Representation representation);
}
