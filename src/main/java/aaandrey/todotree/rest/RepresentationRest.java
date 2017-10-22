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
import aaandrey.todotree.service.IRepresentationService;
import aaandrey.todotree.service.domain.PlainRepresentation;

@RestController
public class RepresentationRest {
	@Autowired
	private IRepresentationService service;

	private Long userId;

	@Authenticational
	@RequestMapping(path = "/representation", method = RequestMethod.POST)
	public ResponseEntity<PlainRepresentation> create(HttpServletRequest request,
			@RequestBody PlainRepresentation representation) {

		PlainRepresentation result = service.create(userId, representation);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/representations", method = RequestMethod.GET)
	public ResponseEntity<List<PlainRepresentation>> getList(HttpServletRequest request) {

		List<PlainRepresentation> result = service.getList(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/representation/{id}", method = RequestMethod.GET)
	public ResponseEntity<PlainRepresentation> getList(HttpServletRequest request, @PathVariable Long id) {

		PlainRepresentation result = service.get(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/representation", method = RequestMethod.PUT)
	public ResponseEntity<PlainRepresentation> update(HttpServletRequest request,
			@RequestBody PlainRepresentation representation) {

		PlainRepresentation result = service.update(userId, representation);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Authenticational
	@RequestMapping(path = "/representation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PlainRepresentation> remove(HttpServletRequest request, @PathVariable Long id) {

		PlainRepresentation result = service.remove(userId, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
