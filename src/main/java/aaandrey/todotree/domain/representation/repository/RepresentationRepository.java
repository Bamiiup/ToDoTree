package aaandrey.todotree.domain.representation.repository;

import aaandrey.todotree.domain.representation.entity.Representation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepresentationRepository extends CrudRepository<Representation, Long> {

    @Query("SELECT representation FROM Representation representation"
            + " JOIN representation.user user WHERE user.id = ?1")
    List<Representation> findByUserId(Long userId);
}
