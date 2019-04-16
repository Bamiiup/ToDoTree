package aaandrey.todotree.domain.user.service;

import aaandrey.todotree.domain.user.entity.User;
import aaandrey.todotree.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        //REVIEW: после правки метода UserRepository.findByLoginAndPassword, здесь будет одно значение,
        //которое будет просто возращаться в return
        return users.size() == 1 ? users.get(0) : null;
    }

}
