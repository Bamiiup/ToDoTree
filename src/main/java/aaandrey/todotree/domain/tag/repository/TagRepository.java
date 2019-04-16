package aaandrey.todotree.domain.tag.repository;

import aaandrey.todotree.domain.tag.entity.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    //REVIEW: заменить list на один результат. Добавить явный @Query
    List<Tag> findByName(String name);
}
