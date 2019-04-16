package aaandrey.todotree.domain.representation.dto;

import aaandrey.todotree.domain.representation.entity.SortRule;
import aaandrey.todotree.domain.tag.dto.PlainTag;
import aaandrey.todotree.domain.todo.entity.PriorityType;

import java.util.HashSet;
import java.util.Set;

public class PlainRepresentation {
    private Long id;
    private Integer dayAmountAfterToday;
    private PriorityType bottomPriority;
    private PriorityType topPriority;
    private Boolean isImportant;
    private Set<PlainTag> tags = new HashSet<>();
    private Integer bottomWeight;
    private Integer topWeight;
    private String name;
    private Long userId;
    private Set<SortRule> sortRules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayAmountAfterToday() {
        return dayAmountAfterToday;
    }

    public void setDayAmountAfterToday(Integer dayAmountAfterToday) {
        this.dayAmountAfterToday = dayAmountAfterToday;
    }

    public PriorityType getBottomPriority() {
        return bottomPriority;
    }

    public void setBottomPriority(PriorityType bottomPriority) {
        this.bottomPriority = bottomPriority;
    }

    public PriorityType getTopPriority() {
        return topPriority;
    }

    public void setTopPriority(PriorityType topPriority) {
        this.topPriority = topPriority;
    }

    public Boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Boolean isImportant) {
        this.isImportant = isImportant;
    }

    public Set<PlainTag> getTags() {
        return tags;
    }

    public void setTags(Set<PlainTag> tags) {
        this.tags = tags;
    }

    public Integer getBottomWeight() {
        return bottomWeight;
    }

    public void setBottomWeight(Integer bottomWeight) {
        this.bottomWeight = bottomWeight;
    }

    public Integer getTopWeight() {
        return topWeight;
    }

    public void setTopWeight(Integer topWeight) {
        this.topWeight = topWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<SortRule> getSortRules() {
        return sortRules;
    }

    public void setSortRules(Set<SortRule> sortRules) {
        this.sortRules = sortRules;
    }

}
