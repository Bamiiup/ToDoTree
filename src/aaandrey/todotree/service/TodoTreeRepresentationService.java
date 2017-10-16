package aaandrey.todotree.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.TodoTreeRepresentation;
import aaandrey.todotree.repositories.TodoTreeRepresentationRepository;
import aaandrey.todotree.repositories.UserRepository;
import aaandrey.todotree.service.domain.PlainTodoTreeRepresentation;
import aaandrey.todotree.service.utils.Converter;

//TODO: implement validation and authorization
@Component
public class TodoTreeRepresentationService implements ITodoTreeRepresentationService {
	@Autowired
	private TodoTreeRepresentationRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ITagService tagService;

	@Override
	@Transactional
	public PlainTodoTreeRepresentation create(Long userId, PlainTodoTreeRepresentation plainRepresentation) {
		TodoTreeRepresentation representation = new TodoTreeRepresentation();

		updatePartOfRepresentation(representation, plainRepresentation);

		representation.setUser(userRepository.findOne(userId));

		repository.save(representation);

		return Converter.toPlain(representation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlainTodoTreeRepresentation> getList(Long userId) {
		return repository.findByUserId(userId).stream().map(Converter::toPlain).collect(Collectors.toList());
	}

	private void updatePartOfRepresentation(TodoTreeRepresentation target, PlainTodoTreeRepresentation source) {
		target.setBottomPriority(source.getBottomPriority());
		target.setBottomWeight(source.getBottomWeight());
		target.setDayAmountAfterToday(source.getDayAmountAfterToday());
		target.setIsImportant(source.getIsImportant());
		target.setTopPriority(source.getTopPriority());
		target.setTopWeight(source.getTopWeight());
		target.setTags(tagService.findOrCreate(source.getTags()));
	}

	@Override
	@Transactional
	public PlainTodoTreeRepresentation update(Long userId, PlainTodoTreeRepresentation plainRepresentation) {
		TodoTreeRepresentation representation = repository.findOne(plainRepresentation.getId());

		updatePartOfRepresentation(representation, plainRepresentation);

		return Converter.toPlain(representation);
	}

	@Override
	@Transactional
	public PlainTodoTreeRepresentation remove(Long userId, Long id) {
		PlainTodoTreeRepresentation result = Converter.toPlain(repository.findOne(id));

		repository.delete(id);

		return result;
	}

}
