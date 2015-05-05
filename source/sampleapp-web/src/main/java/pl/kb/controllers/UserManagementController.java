package pl.kb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.UserServiceLocal;
import model.security.UserDTO;

@ManagedBean(name = "userManagement")
@SessionScoped
public class UserManagementController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<UserDTO> allUsers;

	@EJB
	private UserServiceLocal userService;

	@PostConstruct
	private void init() {
		allUsers = userService.getAllUsers();
	}

	public List<UserDTO> getAllUsers() {
		return allUsers;
	}

}
