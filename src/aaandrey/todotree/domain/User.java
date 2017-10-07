package aaandrey.todotree.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "LOGIN", unique = true, nullable = false)
	private String login;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user")
	private Set<Todo> todoList = new HashSet<>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Todo> getTodoList() {
		return todoList;
	}
	
	public void addTodo(Todo todo) {
		addTodo(todo, false);
	}
	
	public void addTodo(Todo todo, boolean otherSideWasAffected) {
		getTodoList().add(todo);
		if(otherSideWasAffected) {
			return;
		}
		todo.setUser(this, true);
	}
	
	public void removeTodo(Todo todo) {
		removeTodo(todo, false);
	}
	
	public void removeTodo(Todo todo, boolean otherSideWasAffected) {
		this.getTodoList().remove(todo);
		if(otherSideWasAffected) {
			return;
		}
		todo.removeUser(true);
	}
}
