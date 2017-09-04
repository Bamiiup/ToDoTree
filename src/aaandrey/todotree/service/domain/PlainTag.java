package aaandrey.todotree.service.domain;

import java.util.Set;

public class PlainTag {
	private Long id;
	private String name;
	private Set<Long> todoIds;

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

	public Set<Long> getTodoIds() {
		return todoIds;
	}

	public void setTodoIds(Set<Long> todoIds) {
		this.todoIds = todoIds;
	}
}
