package aaandrey.todotree.rest;

import aaandrey.todotree.rest.security.TokenManager;
import aaandrey.todotree.rest.security.TokenPayload;
import aaandrey.todotree.domain.user.entity.User;
import aaandrey.todotree.domain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.RollbackException;
import java.util.Calendar;
import java.util.Date;

@RestController
public class UserRest {
    @Autowired
    private IUserService service;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {

        try {
            User createdUser = service.create(user);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (RollbackException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@RequestBody User user) {
        User authenticationUser = service.findUserByLoginAndPassword(user.getLogin(), user.getPassword());

        if (authenticationUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        String token = createToken(authenticationUser);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private String createToken(User user) {
        Date createdTime = Calendar.getInstance().getTime();
        TokenPayload payload = new TokenPayload(user.getId(), user.getLogin(), createdTime);

        String token = tokenManager.createToken(payload);

        return token;
    }
}
