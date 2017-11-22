package aaandrey.todotree.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import aaandrey.todotree.domain.Representation;
import aaandrey.todotree.domain.SortRule;
import aaandrey.todotree.repositories.RepresentationRepository;
import aaandrey.todotree.repositories.UserRepository;
import aaandrey.todotree.service.ServiceAuthorizationException.ServiceOperation;
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

	private Set<String> sortedTodoFields = Stream
			.of("name", "comment", "startDate", "endDate", "priority", "important", "weight").collect(Collectors.toSet());

	private void validateForCreateOrUpdate(Long userId, PlainRepresentation plainRepresentation) {
		if (plainRepresentation.getBottomPriority().ordinal() > plainRepresentation.getTopPriority().ordinal()) {
			throw ServiceValidationException.endBeforeStart(Representation.class, "topPriority", "bottomPriority",
					plainRepresentation.getId());
		}

		if (plainRepresentation.getBottomWeight() > plainRepresentation.getTopWeight()) {
			throw ServiceValidationException.endBeforeStart(Representation.class, "topWeight", "bottomWeight",
					plainRepresentation.getId());
		}

		if (plainRepresentation.getDayAmountAfterToday() < 0) {
			throw ServiceValidationException.numberConstraintViolation(Representation.class, "dateAmountAfterToday", 0,
					plainRepresentation.getId());
		}

		if (plainRepresentation.getName() == null || plainRepresentation.getName().isEmpty()) {
			throw ServiceValidationException.fieldIsNullOrEmpty(Representation.class, "name", plainRepresentation.getId());
		}

		plainRepresentation.getSortRules().forEach(sortRule -> {
			if (!sortedTodoFields.contains(sortRule.getFieldName())) {
				throw ServiceValidationException.invalidFieldValue(SortRule.class, "fieldName", sortRule.getFieldName(), null);
			}
		});
	}

	@Override
	@Transactional
	public PlainRepresentation create(Long userId, PlainRepresentation plainRepresentation) {
		validateForCreateOrUpdate(userId, plainRepresentation);

		Representation representation = new Representation();

		updatePartOfRepresentation(userId, representation, plainRepresentation);

		representation.setUser(userRepository.findOne(userId));

		repository.save(representation);

		return Converter.toPlain(representation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlainRepresentation> getList(Long userId) {
		return repository.findByUserId(userId).stream().map(Converter::toPlain).collect(Collectors.toList());
	}

	private void updatePartOfRepresentation(Long userId, Representation target, PlainRepresentation source) {
		target.setBottomPriority(source.getBottomPriority());
		target.setBottomWeight(source.getBottomWeight());
		target.setDayAmountAfterToday(source.getDayAmountAfterToday());
		target.setIsImportant(source.getIsImportant());
		target.setName(source.getName());
		target.setTopPriority(source.getTopPriority());
		target.setTopWeight(source.getTopWeight());
		target.setTags(tagService.findOrCreate(userId, source.getTags()));
		target.setSortRules(source.getSortRules());
	}

	private void authorizationForUpdate(Long userId, PlainRepresentation plainRepresentation) {
		Representation representation = repository.findOne(plainRepresentation.getId());
		if (!representation.getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Representation.class, representation.getId(),
					ServiceOperation.update);
		}
	}

	@Override
	@Transactional
	public PlainRepresentation update(Long userId, PlainRepresentation plainRepresentation) {
		validateForCreateOrUpdate(userId, plainRepresentation);
		authorizationForUpdate(userId, plainRepresentation);

		Representation representation = repository.findOne(plainRepresentation.getId());

		updatePartOfRepresentation(userId, representation, plainRepresentation);

		return Converter.toPlain(representation);
	}

	private void authorizationForRemove(Long userId, Long id) {
		Representation representation = repository.findOne(id);
		if (!representation.getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Representation.class, representation.getId(),
					ServiceOperation.remove);
		}
	}

	private void validateForRemove(Long userId, Long id) {
		if (repository.findOne(id) == null) {
			throw ServiceValidationException.entityNotExist(Representation.class, id);
		}
	}

	@Override
	@Transactional
	public PlainRepresentation remove(Long userId, Long id) {
		validateForRemove(userId, id);
		authorizationForRemove(userId, id);

		PlainRepresentation result = Converter.toPlain(repository.findOne(id));

		repository.delete(id);

		return result;
	}

	private void authorizationForGet(Long userId, Long id) {
		Representation representation = repository.findOne(id);
		if (!representation.getUser().getId().equals(userId)) {
			throw ServiceAuthorizationException.entityAccessDenied(Representation.class, representation.getId(),
					ServiceOperation.get);
		}
	}

	@Override
	@Transactional
	public PlainRepresentation get(Long userId, Long id) {
		authorizationForGet(userId, id);

		return Converter.toPlain(repository.findOne(id));
	}

}
