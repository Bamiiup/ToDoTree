package aaandrey.todotree.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import aaandrey.todotree.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByLoginAndPassword(String login, String password);
}
