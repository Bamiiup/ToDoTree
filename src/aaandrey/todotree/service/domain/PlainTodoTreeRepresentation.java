package aaandrey.todotree.service.domain;

import java.util.HashSet;
import java.util.Set;

import aaandrey.todotree.domain.PriorityType;

public class PlainTodoTreeRepresentation {
	private Long id;
	private Integer dayAmountAfterToday;
	private PriorityType bottomPriority;
	private PriorityType topPriority;
	private Boolean isImportant;
	private Set<PlainTag> tags = new HashSet<>();
	private Integer bottomWeight;
	private Integer topWeight;
	private Long userId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
