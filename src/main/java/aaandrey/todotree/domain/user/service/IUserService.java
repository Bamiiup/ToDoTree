package aaandrey.todotree.domain.user.service;

import aaandrey.todotree.domain.user.entity.User;

public interface IUserService {

    User create(User user);

    User findUserByLoginAndPassword(String login, String password);

}