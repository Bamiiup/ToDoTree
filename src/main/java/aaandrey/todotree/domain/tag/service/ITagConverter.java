package aaandrey.todotree.domain.tag.service;

import aaandrey.todotree.domain.tag.dto.PlainTag;
import aaandrey.todotree.domain.tag.entity.Tag;

public interface ITagConverter {
    PlainTag toPlain(Tag tag);
}
