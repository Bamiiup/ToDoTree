package aaandrey.todotree.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import aaandrey.todotree.domain.User;
import aaandrey.todotree.service.utils.TransactionUtils;

@Component("userService")
public class UserService {
	@Resource(name = "transactionUtils")
	private TransactionUtils transactionUtils;

	public User create(User user) {
		return transactionUtils.performInsideTransaction(entityManager -> {
			entityManager.persist(user);

			User result = new User();
			result.setLogin(user.getLogin());
			result.setId(user.getId());

			return result;
		});
	}

	public User findUserByLoginAndPassword(String login, String password) {
		return transactionUtils.performInsideTransaction(entityManager -> {
			List<User> users = entityManager
					.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :password",
							User.class)
					.setParameter("login", login).setParameter("password", password).getResultList();

			return users.size() == 1 ? users.get(0) : null;
		});
	}

}
