package aaandrey.todotree.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.Representation;
import aaandrey.todotree.repositories.RepresentationRepository;
import aaandrey.todotree.repositories.UserRepository;
import aaandrey.todotree.service.domain.PlainRepresentation;
import aaandrey.todotree.service.utils.Converter;

//TODO: implement validation and authorization
@Component
public class RepresentationService implements IRepresentationService {
	@Autowired
	private RepresentationRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ITagService tagService;

	@Override
	@Transactional
	public PlainRepresentation create(Long userId, PlainRepresentation plainRepresentation) {
		Representation representation = new Representation();

		updatePartOfRepresentation(representation, plainRepresentation);

		representation.setUser(userRepository.findOne(userId));

		repository.save(representation);

		return Converter.toPlain(representation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlainRepresentation> getList(Long userId) {
		return repository.findByUserId(userId).stream().map(Converter::toPlain).collect(Collectors.toList());
	}

	private void updatePartOfRepresentation(Representation target, PlainRepresentation source) {
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
	public PlainRepresentation update(Long userId, PlainRepresentation plainRepresentation) {
		Representation representation = repository.findOne(plainRepresentation.getId());

		updatePartOfRepresentation(representation, plainRepresentation);

		return Converter.toPlain(representation);
	}

	@Override
	@Transactional
	public PlainRepresentation remove(Long userId, Long id) {
		PlainRepresentation result = Converter.toPlain(repository.findOne(id));

		repository.delete(id);

		return result;
	}

	@Override
	@Transactional
	public PlainRepresentation get(Long userId, Long id) {
		return Converter.toPlain(repository.findOne(id));
	}

}
