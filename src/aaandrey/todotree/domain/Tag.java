package aaandrey.todotree.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAG")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "tags")
	private Set<Todo> todoList = new HashSet<>();

	public Tag() {

	}

	public Tag(String name) {
		this.name = name;
	}

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

	public Set<Todo> getTodoList() {
		return todoList;
	}

	public void addTodo(Todo todo) {
		addTodo(todo, false);
	}

	public void addTodo(Todo todo, boolean otherSideHasBeenAlreadySet) {
		getTodoList().add(todo);
		if (otherSideHasBeenAlreadySet) {
			return;
		}

		todo.addTag(this, true);
	}

	public void removeTodo(Todo todo) {
		removeTodo(todo, false);
	}

	public void removeTodo(Todo todo, boolean otherSideRemoved) {
		this.getTodoList().remove(todo);
		if (otherSideRemoved) {
			return;
		}
		todo.removeTag(this, true);
	}

	public int hash() {
		return Objects.hash(this.getId(), this.getName());
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (!(object instanceof Tag)) {
			return false;
		}

		Tag that = (Tag) object;

		if (!Objects.equals(this.getId(), that.getId())) {
			return false;
		}

		if (!Objects.equals(this.getName(), this.getName())) {
			return false;
		}

		return true;
	}

}
