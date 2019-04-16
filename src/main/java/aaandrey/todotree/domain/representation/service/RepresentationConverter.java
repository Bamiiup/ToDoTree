package aaandrey.todotree.domain.representation.service;

import aaandrey.todotree.domain.representation.dto.PlainRepresentation;
import aaandrey.todotree.domain.representation.entity.Representation;
import aaandrey.todotree.domain.tag.service.ITagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RepresentationConverter implements IRepresentationConverter {

    @Autowired
    private ITagConverter tagConverter;

    @Autowired
    private ISortRuleCopier sortRuleCopier;

    @Override
    public PlainRepresentation toPlain(Representation representation) {
        PlainRepresentation result = new PlainRepresentation();

        result.setBottomPriority(representation.getBottomPriority());
        result.setBottomWeight(representation.getBottomWeight());
        result.setDayAmountAfterToday(representation.getDayAmountAfterToday());
        result.setId(representation.getId());
        result.setName(representation.getName());
        result.setIsImportant(representation.getIsImportant());
        result.setTags(representation.getTags().stream().map(tagConverter::toPlain).collect(Collectors.toSet()));
        result.setTopPriority(representation.getTopPriority());
        result.setTopWeight(representation.getTopWeight());
        result.setUserId(representation.getUser().getId());
        result.setSortRules(representation.getSortRules().stream().map(sortRuleCopier::copy).collect(Collectors.toSet()));

        return result;
    }
}
