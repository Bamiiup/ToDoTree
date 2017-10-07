package aaandrey.todotree.service.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import aaandrey.todotree.domain.PriorityType;

public class PlainTodo {
	private Long id;

	private String name;

	private String comment;

	private Date startDate;

	private Date endDate;

	private PriorityType priority;

	private Boolean important;

	private Long weight;

	private Long parentId;

	private Set<Long> childIds = new HashSet<>();

	private Long userId;

	private Set<PlainTag> tags = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PriorityType getPriority() {
		return priority;
	}

	public void setPriority(PriorityType priority) {
		this.priority = priority;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Set<Long> getChildIds() {
		return childIds;
	}

	public void setChildIds(Set<Long> childIds) {
		this.childIds = childIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<PlainTag> getTags() {
		return tags;
	}

	public void setTags(Set<PlainTag> tags) {
		this.tags = tags;
	}

}
