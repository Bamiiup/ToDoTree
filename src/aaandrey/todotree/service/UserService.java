package aaandrey.todotree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.User;

@Component("userService")
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public User create(User user) {
		entityManager.persist(user);

		User result = new User();
		result.setLogin(user.getLogin());
		result.setId(user.getId());

		return result;
	}

	@Transactional
	public User findUserByLoginAndPassword(String login, String password) {
		List<User> users = entityManager
				.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :password", User.class)
				.setParameter("login", login).setParameter("password", password).getResultList();

		return users.size() == 1 ? users.get(0) : null;
	}

}
