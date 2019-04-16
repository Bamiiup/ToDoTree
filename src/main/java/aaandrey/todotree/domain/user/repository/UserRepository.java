package aaandrey.todotree.domain.user.repository;

import aaandrey.todotree.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    //REVIEW: заменить на реальный query, возращающий одно значение
    List<User> findByLoginAndPassword(String login, String password);
}
