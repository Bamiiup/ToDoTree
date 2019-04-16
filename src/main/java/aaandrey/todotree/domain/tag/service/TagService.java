package aaandrey.todotree.domain.tag.service;

import aaandrey.todotree.domain.tag.dto.PlainTag;
import aaandrey.todotree.domain.tag.entity.Tag;
import aaandrey.todotree.domain.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService implements ITagService {

    @Autowired
    private TagRepository repository;

    @Override
    @Transactional
    public List<Tag> findOrCreate(Collection<PlainTag> plainTags) {
        return plainTags.stream().map(plainTag -> {

            List<Tag> foundTags = repository.findByName(plainTag.getName());

            //REVIEW: после замены на query,
            //(см. TagRepository.findByName)
            //возвращаюший одно значение здесь будет проверка на null вместо size == 1
            if (foundTags.size() == 1) {
                return foundTags.get(0);
            } else {
                Tag tag = new Tag(plainTag.getName());
                repository.save(tag);
                return tag;
            }
        }).collect(Collectors.toList());
    }
}
