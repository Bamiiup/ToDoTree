package aaandrey.todotree.domain.user.dto;

import java.util.Set;

public class PlainUser {
    private Long id;
    private String login;
    private String password;
    private Set<Long> todoIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Long> getTodoIds() {
        return todoIds;
    }

    public void setTodoIds(Set<Long> todoIds) {
        this.todoIds = todoIds;
    }

}
