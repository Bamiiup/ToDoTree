package aaandrey.todotree.service;

import aaandrey.todotree.domain.User;

public interface IUserService {

	User create(User user);

	User findUserByLoginAndPassword(String login, String password);

}