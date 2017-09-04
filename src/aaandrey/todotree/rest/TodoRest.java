package aaandrey.todotree.rest;

import java.util.function.Function;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aaandrey.todotree.rest.utils.AuthenticationUtils;
import aaandrey.todotree.security.TokenManager;
import aaandrey.todotree.security.TokenPayload;
import aaandrey.todotree.service.TodoService;
import aaandrey.todotree.service.domain.PlainTodo;

@RestController
public class TodoRest {
	@Resource(name = "todoService")
	private TodoService todoService;

	@Resource(name = "authenticationUtils")
	private AuthenticationUtils authenticationUtils;

	@RequestMapping(path = "/todo", method = RequestMethod.POST)
	public ResponseEntity<PlainTodo> create(HttpServletRequest request, @RequestBody PlainTodo plainTodo) {
		return authenticationUtils.peformAfterAuthentication(request, userId -> {
			PlainTodo result = todoService.create(userId, plainTodo);

			return new ResponseEntity<>(result, HttpStatus.OK);
		});
	}

}
