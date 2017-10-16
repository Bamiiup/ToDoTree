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
import aaandrey.todotree.service.ITodoTreeRepresentationService;
import aaandrey.todotree.service.domain.PlainTodoTreeRepresentation;

@RestController
public class TodoTreeRepresentation {
	@Autowired
	private ITodoTreeRepresentationService service;

	private Long userId;

	@Authenticational
	@RequestMapping(path = "/todoTreeRepresentation", method = RequestMethod.POST)
	public ResponseEntity<PlainTodoTreeRepresentation> create(HttpServletRequest request,
			@RequestBody PlainTodoTreeRepresentation representation) {

		PlainTodoTreeRepresentation result = service.create(userId, representation);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/todoTreeRepresentations", method = RequestMethod.GET)
	public ResponseEntity<List<PlainTodoTreeRepresentation>> getList(HttpServletRequest request) {

		List<PlainTodoTreeRepresentation> result = service.getList(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/todoTreeRepresentation", method = RequestMethod.PUT)
	public ResponseEntity<PlainTodoTreeRepresentation> update(HttpServletRequest request,
			@RequestBody PlainTodoTreeRepresentation representation) {

		PlainTodoTreeRepresentation result = service.update(userId, representation);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/todoTreeRepresentation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PlainTodoTreeRepresentation> remove(HttpServletRequest request, @PathVariable Long id) {

		PlainTodoTreeRepresentation result = service.remove(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
