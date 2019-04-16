package aaandrey.todotree.domain.representation.service;

import aaandrey.todotree.domain.representation.dto.PlainRepresentation;
import aaandrey.todotree.domain.representation.entity.Representation;
import aaandrey.todotree.domain.representation.repository.RepresentationRepository;
import aaandrey.todotree.domain.tag.service.ITagService;
import aaandrey.todotree.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepresentationService implements IRepresentationService {
    @Autowired
    private RepresentationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITagService tagService;

    @Autowired
    private IRepresentationConverter representationConverter;

    @Override
    @Transactional
    public PlainRepresentation create(Long userId, PlainRepresentation plainRepresentation) {
        Representation representation = new Representation();

        updatePartOfRepresentation(representation, plainRepresentation);

        representation.setUser(userRepository.findOne(userId));

        repository.save(representation);

        return representationConverter.toPlain(representation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlainRepresentation> getList(Long userId) {
        return repository.findByUserId(userId).stream().map(representationConverter::toPlain).collect(Collectors.toList());
    }

    private void updatePartOfRepresentation(Representation target, PlainRepresentation source) {
        target.setBottomPriority(source.getBottomPriority());
        target.setBottomWeight(source.getBottomWeight());
        target.setDayAmountAfterToday(source.getDayAmountAfterToday());
        target.setIsImportant(source.getIsImportant());
        target.setName(source.getName());
        target.setTopPriority(source.getTopPriority());
        target.setTopWeight(source.getTopWeight());
        target.setTags(tagService.findOrCreate(source.getTags()));
        target.setSortRules(source.getSortRules());
    }

    @Override
    @Transactional
    public PlainRepresentation update(Long userId, PlainRepresentation plainRepresentation) {
        Representation representation = repository.findOne(plainRepresentation.getId());

        updatePartOfRepresentation(representation, plainRepresentation);

        return representationConverter.toPlain(representation);
    }

    @Override
    @Transactional
    public PlainRepresentation remove(Long userId, Long id) {
        PlainRepresentation result = representationConverter.toPlain(repository.findOne(id));

        repository.delete(id);

        return result;
    }

    @Override
    @Transactional
    public PlainRepresentation get(Long userId, Long id) {
        return representationConverter.toPlain(repository.findOne(id));
    }

}
