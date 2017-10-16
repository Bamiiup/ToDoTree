package aaandrey.todotree.repositories;

import org.springframework.data.repository.CrudRepository;

import aaandrey.todotree.domain.User;

public interface UserCrud extends CrudRepository<User, Long> {

}
