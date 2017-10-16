package aaandrey.todotree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.User;
import aaandrey.todotree.repositories.UserRepository;

@Component
public class UserService implements IUserService {
	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public User create(User user) {
		repository.save(user);

		User result = new User();
		result.setLogin(user.getLogin());
		result.setId(user.getId());

		return result;
	}

	@Override
	@Transactional
	public User findUserByLoginAndPassword(String login, String password) {
		List<User> users = repository.findByLoginAndPassword(login, password);

		return users.size() == 1 ? users.get(0) : null;
	}

}
