package model.security;

import java.util.Set;

import model.entity.Role.UserRoleName;
import model.entity.User.UserState;

public class UserDTO {

	private int id;
	private String username;
	private UserState state;
	private Set<UserRoleName> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<UserRoleName> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoleName> roles) {
		this.roles = roles;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public UserState getState() {
		return state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
