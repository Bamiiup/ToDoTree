package aaandrey.todotree.domain.representation.service;

import aaandrey.todotree.domain.representation.entity.SortRule;
import org.springframework.stereotype.Service;

@Service
public class SortRuleCopier implements ISortRuleCopier {

    @Override
    public SortRule copy(SortRule sortRule) {
        SortRule result = new SortRule();
        result.setFieldName(sortRule.getFieldName());
        result.setIsDirect(sortRule.getIsDirect());
        result.setOrder(sortRule.getOrder());

        return result;
    }
}
