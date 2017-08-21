package aaandrey.todotree.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import aaandrey.todotree.domain.User;

@Component("userService")
public class UserService {
	@Resource(name = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	public User create(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		try {

			entityManager.persist(user);
			entityManager.getTransaction().commit();

			User result = new User();
			result.setLogin(user.getLogin());
			result.setId(user.getId());
			return result;

		} finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}

			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	public User findUserByLoginAndPassword(String login, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		try {

			List<User> users = entityManager
					.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :password", User.class)
					.setParameter("login", login).setParameter("password", password).getResultList();

			entityManager.getTransaction().commit();
			
			return users.size() == 1 ? users.get(0) : null;

		} finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}

			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}
}
