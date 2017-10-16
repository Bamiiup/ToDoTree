package aaandrey.todotree.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aaandrey.todotree.rest.aspect.Authenticational;
import aaandrey.todotree.service.ITodoService;
import aaandrey.todotree.service.domain.PlainTodo;

@RestController
public class TodoRest {
	@Autowired
	private ITodoService service;

	private Long userId;

	@RequestMapping(path = "/todo", method = RequestMethod.POST)
	@Authenticational
	public ResponseEntity<PlainTodo> create(HttpServletRequest request, @RequestBody PlainTodo plainTodo) {
		PlainTodo result = service.create(userId, plainTodo);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
	@Authenticational
	public ResponseEntity<PlainTodo> get(HttpServletRequest request, @PathVariable Long id) {
		PlainTodo result = service.get(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo", method = RequestMethod.PUT)
	@Authenticational
	public ResponseEntity<PlainTodo> update(HttpServletRequest request, @RequestBody PlainTodo plainTodo) {
		PlainTodo result = service.update(userId, plainTodo);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todoList", method = RequestMethod.GET)
	@Authenticational
	public ResponseEntity<List<PlainTodo>> getList(HttpServletRequest request) {
		List<PlainTodo> result = service.getList(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo/{id}", method = RequestMethod.DELETE)
	@Authenticational
	public ResponseEntity<PlainTodo> remove(HttpServletRequest request, @PathVariable Long id) {
		PlainTodo result = service.remove(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
