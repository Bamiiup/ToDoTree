package aaandrey.todotree.security;

import java.util.Date;

public class TokenPayload {
	private Long userId;
	private String login;
	private Date createdTime;

	public TokenPayload(Long userId, String login, Date createdTime) {
		this.userId = userId;
		this.login = login;
		this.createdTime = createdTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
