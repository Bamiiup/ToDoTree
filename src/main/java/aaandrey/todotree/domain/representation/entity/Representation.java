package aaandrey.todotree.domain.representation.entity;

import aaandrey.todotree.domain.tag.entity.Tag;
import aaandrey.todotree.domain.todo.entity.PriorityType;
import aaandrey.todotree.domain.user.entity.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REPRESENTATION")
public class Representation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DAY_AMOUNT_AFTER_TODAY")
    private Integer dayAmountAfterToday;

    @ManyToMany
    @JoinTable(name = "REPRESENTATION_TAG", joinColumns = @JoinColumn(name = "REPRESENTATION_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "BOTTOM_PRIORITY")
    private PriorityType bottomPriority;

    @Column(name = "TOP_PRIORITY")
    private PriorityType topPriority;

    @Column(name = "IS_IMPORTANT")
    private Boolean isImportant;

    @Column(name = "BOTTOM_WEIGHT")
    private Integer bottomWeight;

    @Column(name = "TOP_WEIGHT")
    private Integer topWeight;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "REPRESENTATION__SORT_RULE", joinColumns = @JoinColumn(name = "REPRESENTATION_ID"))
    private Set<SortRule> sortRules = new HashSet<>();

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.clear();
        tags.forEach(this.tags::add);
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        setUser(user, false);
    }

    public void setUser(User user, boolean otherSideWasAffected) {
        this.user = user;
        if (otherSideWasAffected) {
            return;
        }

        user.addRepresentation(this, true);
    }

    public Set<SortRule> getSortRules() {
        return sortRules;
    }

    public void setSortRules(Collection<SortRule> sortRules) {
        this.getSortRules().clear();
        sortRules.forEach(this.sortRules::add);
    }
}
