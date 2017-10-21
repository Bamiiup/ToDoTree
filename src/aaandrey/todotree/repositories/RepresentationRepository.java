package aaandrey.todotree.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aaandrey.todotree.domain.Representation;

public interface RepresentationRepository extends CrudRepository<Representation, Long> {

	@Query("SELECT representation FROM Representation representation"
			+ " JOIN representation.user user WHERE user.id = ?1")
	List<Representation> findByUserId(Long userId);
}
