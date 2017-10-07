package aaandrey.todotree.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aaandrey.todotree.rest.aspect.Authenticational;
import aaandrey.todotree.rest.utils.AuthenticationUtils;
import aaandrey.todotree.service.ITodoService;
import aaandrey.todotree.service.domain.PlainTodo;

@RestController
public class TodoRest {
	@Resource(name = "todoService")
	private ITodoService todoService;

	@Resource(name = "authenticationUtils")
	private AuthenticationUtils authenticationUtils;

	private Long userId;

	@RequestMapping(path = "/todo", method = RequestMethod.POST)
	@Authenticational
	public ResponseEntity<PlainTodo> create(HttpServletRequest request, @RequestBody PlainTodo plainTodo) {
		PlainTodo result = todoService.create(userId, plainTodo);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
	@Authenticational
	public ResponseEntity<PlainTodo> get(HttpServletRequest request, @PathVariable Long id) {
		PlainTodo result = todoService.get(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo", method = RequestMethod.PUT)
	@Authenticational
	public ResponseEntity<PlainTodo> update(HttpServletRequest request, @RequestBody PlainTodo plainTodo) {
		PlainTodo result = todoService.update(userId, plainTodo);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todoList", method = RequestMethod.GET)
	@Authenticational
	public ResponseEntity<List<PlainTodo>> getList(HttpServletRequest request) {
		List<PlainTodo> result = todoService.getList(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo/{id}", method = RequestMethod.DELETE)
	@Authenticational
	public ResponseEntity<PlainTodo> remove(HttpServletRequest request, @PathVariable Long id) {
		PlainTodo result = todoService.remove(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
